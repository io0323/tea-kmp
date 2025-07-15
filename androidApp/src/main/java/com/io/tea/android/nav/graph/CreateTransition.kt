package com.io.tea.android.nav.graph

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

internal fun createTransition(
    route: String,
): Transition {
    return Transition(
        route = route,
        enterTransition = slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth / 2 },
            animationSpec = tween()
        ) + fadeIn(),
        exitTransition = slideOutHorizontally(
            targetOffsetX = { fullWidth -> fullWidth / 2 },
            animationSpec = tween()
        ) + fadeOut(),
        popEnterTransition = slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth / 2 },
            animationSpec = tween()
        ) + fadeIn(),
        popExitTransition = slideOutHorizontally(
            targetOffsetX = { fullWidth -> fullWidth / 2 },
            animationSpec = tween()
        ) + fadeOut()
    )
}

data class Transition(
    val route: String,
    val enterTransition: EnterTransition,
    val exitTransition: ExitTransition,
    val popEnterTransition: EnterTransition,
    val popExitTransition: ExitTransition
)
