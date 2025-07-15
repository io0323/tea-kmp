package com.io.tea.android

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.io.tea.android.nav.AppCompositionLocal
import com.io.tea.android.nav.bottom.MainBottomNavViewModel
//import com.io.tea.android.nav.bottom.MainBottomNavViewModel
import com.io.tea.android.ui.MainViewModel
import com.io.tea.android.ui.login.LoginDestination
import com.io.tea.android.ui.mock.MockDummyDestination
import com.io.tea.android.ui.rememberWindowSizeClass
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()
    private val bottomNavViewModel: MainBottomNavViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(this.window, false)
        setContent {
            AppCompositionLocal(
                rememberWindowSizeClass(),
                viewModel,
                bottomNavViewModel,
            )
        }
        viewModel.onCreate(LoginDestination.graphRoute)
//        viewModel.onCreate(MockDummyDestination.graphRoute)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        viewModel.onNewIntent()
    }
}
