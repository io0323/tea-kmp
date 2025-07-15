package com.io.tea.android.ui.user

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.MainActivity
import com.io.tea.android.nav.navigator.LocalNavigator
import com.io.tea.android.ui.MainViewModel
import com.io.tea.android.ui.user.model.UpdateAnimationModel
import com.io.tea.android.ui.user.model.UserInformationScreen
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import kotlin.math.roundToInt

@Composable
internal fun UserInformationPage(
    viewModel: UserInformationViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as MainActivity,
    ),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    val userInformationModel by viewModel.userInformationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val animationModel by viewModel.updateAnimationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val updateAnimationModel = animationModel.copy(
        offsetY = with(LocalDensity.current) {
            UpdateAnimationModel.componentHeight.toPx().roundToInt()
        }
    )

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }

    LaunchedEffect(updateAnimationModel.isVisible) {
        if (updateAnimationModel.isVisible) {
            delay(UpdateAnimationModel.DISPLAY_MILLS)
            viewModel.completeAnimation()
        }
    }

    UserInformationScreen(
        userInformationModel = userInformationModel,
        animationModel = updateAnimationModel,
        onClick = { viewModel.onClick() }
    )
}