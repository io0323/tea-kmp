package com.io.tea.android.ui.user.model

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.ui.common.BottomBar
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.user.UserInformationViewModel
import com.io.tea.android.ui.user.block.UpdateAnimationBlock
import com.io.tea.android.ui.user.block.UserAddressBlock
import com.io.tea.android.ui.user.block.UserBirthBlock
import com.io.tea.android.ui.user.block.UserGenderBlock
import com.io.tea.android.ui.user.block.UserZipBlock
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun UserInformationScreen(
    userInformationModel: UserInformationModel,
    animationModel: UpdateAnimationModel,
    onClick: () -> Unit,
    viewModel: UserInformationViewModel = koinViewModel(),
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.user_information__header_title),
                onPopBack = {}
            )
        },
        bottomBar = {
            BottomBar(
                resourceId = R.string.user_information__button_label,
                onClick = onClick
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                UserBirthBlock(birth = userInformationModel.birth)
                UserGenderBlock(gender = userInformationModel.gender)

                if (userInformationModel.isJapanCountryCode) {
                    UserZipBlock(zip = userInformationModel.zip)
                    UserAddressBlock(address = userInformationModel.address)
                }
            }

            UpdateAnimationBlock(
                isVisible = animationModel.isVisible,
                offsetY = animationModel.offsetY,
                durationMillis = animationModel.durationMillis,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
