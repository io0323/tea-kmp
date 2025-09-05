package com.io.tea.android.nav.bottom

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.io.tea.android.nav.navigator.LocalNavigator
import com.io.tea.android.resource.theme.TeaAppTheme
import org.koin.androidx.compose.koinViewModel

@SuppressLint("NewApi")
@Composable
fun BottomBar(
    onClickBottomNavItem: OnClickBottomNavItem? = null,
) {
    val viewModel: MainBottomNavViewModel = koinViewModel()
    val navController: NavController = LocalNavigator.current.navController
    val selectBottomNavRoute = BottomNavType.selectBottomNav(navController)
    val bottomNavList by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.bottomNavListModelStateFlow.collectAsStateWithLifecycle(
            androidx.lifecycle.compose.LocalLifecycleOwner.current
        )
    } else {
        viewModel.bottomNavListModelStateFlow.collectAsState(initial = emptyList())
    }
    val defaultOnClick = remember(navController) {
        DefaultOnClickBottomNavItem(navController)
    }
    val currentOnClickBottomNavItem = { screen: BottomNavDestination ->
        (onClickBottomNavItem ?: defaultOnClick).onClickBottomNavItem(screen)
    }
    BottomBar(
        bottomNavList = bottomNavList,
        onClickBottomNavItem = currentOnClickBottomNavItem,
        selectBottomNavRoute = selectBottomNavRoute
    )
}

@Composable
private fun BottomBar(
    bottomNavList: List<BottomNavItemModel>,
    onClickBottomNavItem: (BottomNavDestination) -> Unit,
    selectBottomNavRoute: String?,
) {
    NavigationBar(
        tonalElevation = 0.dp,
    ) {
        bottomNavList.forEachIndexed { _, item ->
            val screen = item.destination
            val selected = screen.value == selectBottomNavRoute
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.bottomNavIconRes),
                        contentDescription = stringResource(screen.bottomNavIcondescription),
                        tint = if (selected) {
                            TeaAppTheme.colors.BottomNavTint
                        } else {
                            TeaAppTheme.colors.Grey900
                        },
                        modifier = Modifier
                            .size(24.dp),
                    )
                },
                label = {
                    Text(
                        text = stringResource(screen.bottomNavTitle),
                        color = if (selected) {
                            TeaAppTheme.colors.BottomNavTint
                        } else {
                            TeaAppTheme.colors.Grey900
                        },
                    )
                },
                selected = selected,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                ),
                onClick = { onClickBottomNavItem(screen) },
            )
        }
    }
}

internal class DefaultOnClickBottomNavItem(
    private val navController: NavController,
) : OnClickBottomNavItem {
    override fun onClickBottomNavItem(
        destination: BottomNavDestination,
    ) {
        destination.navigate(navController)
    }
}