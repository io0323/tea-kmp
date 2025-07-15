package com.io.tea.android.ui.user.input

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.BottomBar
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.dialog.PickerDialog
import com.io.tea.android.ui.user.input.block.AddressBlock
import com.io.tea.android.ui.user.input.block.BirthBlock
import com.io.tea.android.ui.user.input.block.BirthPickerDialog
import com.io.tea.android.ui.user.input.block.GenderBlock
import com.io.tea.android.ui.user.input.block.ZipBlock
import com.io.tea.android.ui.user.input.model.UserInformationInputModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun UserInformationInputScreen(
    viewModel: UserInformationInputViewModel = koinViewModel(),
    inputModel: UserInformationInputModel
) {
    val interactionSource = remember { MutableInteractionSource() }
    val showBirthPickerDialog = remember { mutableStateOf(false) }
    val showGenderPickerDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.user_information_input__header_title),
                onPopBack = {}
            )
        },
        bottomBar = {
            BottomBar(
                resourceId = R.string.user_information_input__button,
                onClick = {}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {

            if (showBirthPickerDialog.value) {
                BirthPickerDialog(
                    yearListState = rememberLazyListState(inputModel.birth.currentYearIndex()),
                    monthListState = rememberLazyListState(inputModel.birth.currentMonthIndex()),
                    yearOgions = inputModel.birth.yearOgions(),
                    monthOgions = inputModel.birth.monthOgions(),
                    onButtonClick = { yearIndex, monthIndex ->
                        showBirthPickerDialog.value = false
                    },
                    onDismissRequest = {
                        showBirthPickerDialog.value = false
                    }
                )
            }

            if (showGenderPickerDialog.value) {
                PickerDialog(
                    ogions = inputModel.genders,
                    onButtonClick = { genderIndex ->
                        showGenderPickerDialog.value = false
                    },
                    onDismissRequest = {
                        showGenderPickerDialog.value = false
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.user_information_input__description),
                style = TeaAppTheme.typography.h4,
            )

            Spacer(modifier = Modifier.height(21.dp))

            BirthBlock(
                text = "",
                onTextFieldClick = {
                    showBirthPickerDialog.value = true
                },
                interactionSource = interactionSource
            )

            Spacer(modifier = Modifier.height(21.dp))

            GenderBlock(
                text = "",
                onTextFieldClick = {
                    showGenderPickerDialog.value = true
                },
                interactionSource = interactionSource
            )

            if (inputModel.isJapanCountryCode) {
                Spacer(modifier = Modifier.height(21.dp))

                ZipBlock(
                    text = "",
                    onSearchButtonClick = {},
                    onValueChange = {},
                    interactionSource = interactionSource
                )

                Spacer(modifier = Modifier.height(21.dp))

                AddressBlock(
                    text = "",
                    interactionSource = interactionSource
                )
            }
        }
    }
}
