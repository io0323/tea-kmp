package com.io.tea.android.util

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme

object TextUtil {

    /**
     * TODO :Constraints（制約）
     * １Textの中にLinkedTextは１つのみ対応する
     * ※ Textの中に複数LinkedTextが存在する場合は最初に合致するLinkedTextのみにLinkが掛かります。
     */
    @Composable
    fun LinkText(
        text: String,
        modifier: Modifier = Modifier,
        linkedText: String,
        link: String,
        linkColor: Color = Colors.BlueDark,
        textFontStyle: TextStyle,
        linkFontStyle: TextStyle,
        onClick: () -> Unit = {},
    ) {
        val annotatedString = buildAnnotatedString {
            append(
                AnnotatedString(
                    text = text,
                    spanStyle = SpanStyle(
                        color = TeaAppTheme.colors.FontSecondary,
                        fontSize = textFontStyle.fontSize,
                        fontFamily = textFontStyle.fontFamily,
                        fontWeight = textFontStyle.fontWeight
                    )
                )
            )

            val startIndex = text.indexOf(linkedText)
            val endIndex = startIndex + linkedText.length
            addStyle(
                style = SpanStyle(
                    color = linkColor,
                    fontSize = linkFontStyle.fontSize,
                    fontFamily = linkFontStyle.fontFamily,
                    fontWeight = linkFontStyle.fontWeight,
                    textDecoration = TextDecoration.Underline

                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = link,
                start = startIndex,
                end = endIndex
            )
        }

        ClickableText(
            text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations("URL", offset, offset).firstOrNull()?.let {
                    onClick()
                }
            },
            modifier = modifier
        )
    }
}