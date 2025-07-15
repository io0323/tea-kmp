package com.io.tea.android.ui.common

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.io.tea.android.ui.common.component.model.BarcodeBackgroundModel
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import io.github.aakira.napier.Napier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

@ExperimentalGetImage
class TeaAppBarcodeCamera {
    private var camera: Camera? = null
    private var processCameraProvider: ProcessCameraProvider? = null

    @Composable
    fun CameraPreview(
        useBackCamera: Boolean = true,
        barcodeBackgroundModel: BarcodeBackgroundModel,
        onBarcodeScanned: (String) -> Unit,
        onTargetDrawn: (Rect) -> Unit,
    ) {
        val lifecycleOwner = LocalLifecycleOwner.current
        val ImageCapture = remember { ImageCapture.Builder().build() }

        Box(modifier = Modifier.fillMaxSize()) {
            CameraPreviewArea(
                lifecycleOwner = lifecycleOwner,
                ImageCapture = ImageCapture,
                useBackCamera = useBackCamera,
                onBarcodeScanned = onBarcodeScanned
            )
            CameraTargetArea(
                barcodeBackgroundModel = barcodeBackgroundModel,
                onTargetDrawn = onTargetDrawn,
            )
        }
    }

    @Composable
    private fun CameraPreviewArea(
        lifecycleOwner: LifecycleOwner,
        ImageCapture: ImageCapture,
        useBackCamera: Boolean,
        onBarcodeScanned: (String) -> Unit
    ) {
        AndroidView(
            factory = { context ->
                PreviewView(context).apply {
                    layoutParams =
                        LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    scaleType = PreviewView.ScaleType.FILL_START

                    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
                    cameraProviderFuture.addListener({
                        startCamera(
                            context = context,
                            previewView = this,
                            lifecycleOwner = lifecycleOwner,
                            ImageCapture = ImageCapture,
                            cameraSelector = cameraSelector(useBackCamera = useBackCamera),
                            onBarcodeScanned = onBarcodeScanned
                        )
                    }, ContextCompat.getMainExecutor(context))
                }
            }
        )
    }

    @Composable
    private fun CameraTargetArea(
        barcodeBackgroundModel: BarcodeBackgroundModel,
        onTargetDrawn: (Rect) -> Unit,
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(barcodeBackgroundModel.backgroundColor)
            .drawWithContent {
                // 矩形の大きさは、幅に合わせる
                val sizePx = size.width - barcodeBackgroundModel.rectangleOffsetX.toPx() * 2
                val topLeft = Offset(
                    (size.width - sizePx) / 2,
                    barcodeBackgroundModel.rectangleOffsetY.toPx()
                )

                // 描画しないエリア
                drawRect(
                    topLeft = topLeft,
                    size = Size(sizePx, sizePx),
                    color = Color.Black, // Clearするので何色でも良い
                    blendMode = BlendMode.Clear
                )

                // 四隅のマーク
                drawCornerLines(
                    drawScope = this,
                    topLeft = topLeft,
                    width = sizePx,
                    height = sizePx,
                    cornerLength = barcodeBackgroundModel.cornerLength.toPx(),
                    strokeWidth = barcodeBackgroundModel.cornerStrokeWidth.toPx(),
                    color = barcodeBackgroundModel.cornerColor
                )

                val rect = Rect(topLeft, Size(sizePx, sizePx))
                onTargetDrawn(rect)
            }
        )
    }

    fun stopCamera() {
        processCameraProvider?.unbindAll()
    }

    // NOTE: 第２フェーズで使用予定
    // fun toggleFlash(
    // enabledFlash: Boolean,
    // ) {
    // camera?.cameraControl?.enableTorch(enabledFlash)
    // }

    private fun drawCornerLines(
        drawScope: DrawScope,
        topLeft: Offset,
        width: Float,
        height: Float,
        cornerLength: Float,
        strokeWidth: Float,
        color: Color
    ) {
        with(drawScope) {
            // 左上
            drawLine(
                color = color,
                start = Offset(topLeft.x - strokeWidth / 2, topLeft.y),
                end = Offset(topLeft.x + cornerLength, topLeft.y),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = color,
                start = Offset(topLeft.x, topLeft.y - strokeWidth / 2),
                end = Offset(topLeft.x, topLeft.y + cornerLength),
                strokeWidth = strokeWidth
            )

            // 右上
            drawLine(
                color = color,
                start = Offset(topLeft.x + width - cornerLength, topLeft.y),
                end = Offset(topLeft.x + width + strokeWidth / 2, topLeft.y),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = color,
                start = Offset(topLeft.x + width, topLeft.y - strokeWidth / 2),
                end = Offset(topLeft.x + width, topLeft.y + cornerLength),
                strokeWidth = strokeWidth
            )

            // 左下
            drawLine(
                color = color,
                start = Offset(topLeft.x - strokeWidth / 2, topLeft.y + height),
                end = Offset(topLeft.x + cornerLength, topLeft.y + height),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = color,
                start = Offset(topLeft.x, topLeft.y + height - cornerLength),
                end = Offset(topLeft.x, topLeft.y + height + strokeWidth / 2),
                strokeWidth = strokeWidth
            )

            // 右下
            drawLine(
                color = color,
                start = Offset(topLeft.x + width - cornerLength, topLeft.y + height),
                end = Offset(topLeft.x + width + strokeWidth / 2, topLeft.y + height),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = color,
                start = Offset(topLeft.x + width, topLeft.y + height - cornerLength),
                end = Offset(topLeft.x + width, topLeft.y + height + strokeWidth / 2),
                strokeWidth = strokeWidth
            )
        }
    }

    private fun cameraSelector(useBackCamera: Boolean): CameraSelector {
        return if (useBackCamera) {
            CameraSelector.DEFAULT_BACK_CAMERA
        } else {
            CameraSelector.DEFAULT_FRONT_CAMERA
        }
    }

    private fun startCamera(
        context: Context,
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        ImageCapture: ImageCapture,
        cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
        onBarcodeScanned: (String) -> Unit
    ) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            processCameraProvider = cameraProvider

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.surfaceProvider = previewView.surfaceProvider
                }

            val imageAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()

            val ogions = BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
                .build()

            val scanner = BarcodeScanning.getClient(ogions)

            imageAnalysis.setAnalyzer(Dispatchers.Default.asExecutor()) { imageProxy ->
                processImageProxy(
                    barcodeScanner = scanner,
                    imageProxy = imageProxy,
                    onSuccess = onBarcodeScanned
                )
            }

            try {
                cameraProvider.unbindAll()
                camera = cameraProvider.bindToLifecycle(
                    lifecycleOwner = lifecycleOwner,
                    cameraSelector = cameraSelector,
                    preview, ImageCapture, imageAnalysis
                )
            } catch (Exception: Exception) {
                Napier.d("binding failed: ${Exception.message}")
            }

        }, ContextCompat.getMainExecutor(context))
    }

    private fun processImageProxy(
        barcodeScanner: BarcodeScanner,
        imageProxy: ImageProxy,
        onSuccess: (String) -> Unit
    ) {
        imageProxy.image?.let { image ->
            val inputImage =
                InputImage.fromMediaImage(
                    image,
                    imageProxy.imageInfo.rotationDegrees
                )

            barcodeScanner.process(inputImage)
                .addOnSuccessListener { barcodeList ->
                    val barcode = barcodeList.getOrNull(0)
                    barcode?.displayValue?.let {
                        onSuccess(it)
                    }
                }
                .addOnFailureListener {
                    Log.e(ContentValues.TAG, it.message.orEmpty())
                }.addOnCompleteListener {
                    imageProxy.image?.close()
                    imageProxy.close()
                }
        }
    }
}
