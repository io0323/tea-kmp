package com.io.tea.android.ui.unstart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.io.tea.android.nav.bottom.BottomBar
import com.io.tea.android.nav.bottom.OnClickBottomNavItem
import com.io.tea.android.ui.common.component.ButtonChevron
import com.io.tea.android.ui.common.component.ButtonClose
import com.io.tea.android.ui.common.component.ButtonHomePage
import com.io.tea.android.ui.common.component.ButtonPhone
import com.io.tea.android.ui.common.component.ButtonRoute
import com.io.tea.android.ui.common.component.ButtonSearch
import com.io.tea.android.ui.dialog.TextDialog
import com.io.tea.android.ui.dialog.WebViewDialog

@Composable
internal fun NearbyStoreScreen(
    onClickBottomNavItem: OnClickBottomNavItem,
    onPopBack: () -> Unit = {},
    onClick: (String) -> Unit = {},
) {

    var isWebViewDialog by remember { mutableStateOf(false) }
    var isTextViewDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {},
        bottomBar = {
            BottomBar(onClickBottomNavItem = onClickBottomNavItem)
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ButtonChevron(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = 16.dp, y = 16.dp),
                        onClick = {
                            onPopBack()
                        }
                    )
                }

                // TODO: ボタンのレイアウト確認用なので、本実装で削除
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                ) {
                    ButtonChevron(onClick = {})
                    ButtonClose(onClick = {})
                    ButtonSearch(onClick = {})
                    ButtonRoute(onClick = {})
                    ButtonPhone(onClick = {})
                    ButtonHomePage(onClick = {})
                }

                Button(onClick = { onClick("1") }) {
                    Text("XXX画面に遷移します")
                }
                Button(
                    onClick = { isWebViewDialog = true },
                ) {
                    Text("WebView Dialogを表示します")
                }
                Button(
                    onClick = { isTextViewDialog = true },
                ) {
                    Text("TextView Dialogを表示します")
                }
            }

            if (isWebViewDialog) {
                WebViewDialog(
                    title = "「さかいプレミアム商品券」\nアプリ利用規約",
                    url = "https://www.google.com",
                    onDismissClick = { isWebViewDialog = false },
                )
            }
            if (isTextViewDialog) {
                TextDialog(
                    title = "「さかいプレミアム商品券」\nアプリ利用規約",
                    text = "https://www.google.com",
                    onDismissClick = { isTextViewDialog = false },
                )
            }
        }
    }
}
