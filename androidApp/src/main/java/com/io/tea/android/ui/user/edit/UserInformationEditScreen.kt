package com.io.tea.android.ui.user.edit

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.ui.common.BottomBar
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.dialog.PickerDialog
import com.io.tea.android.ui.user.input.block.AddressBlock
import com.io.tea.android.ui.user.input.block.BirthBlock
import com.io.tea.android.ui.user.input.block.BirthPickerDialog
import com.io.tea.android.ui.user.input.block.GenderBlock
import com.io.tea.android.ui.user.input.block.ZipBlock
import com.io.tea.android.ui.user.model.UserInformationEditModel
import com.io.tea.android.util.DateUtil
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun UserInformationEditScreen(
    editModel: UserInformationEditModel,
    genders: List<String>,
    currentGenderIndex: Int,
    viewModel: UserInformationEditViewModel = koinViewModel(),
) {
    val interactionSource = remember { MutableInteractionSource() }
    val showBirthPickerDialog = remember { mutableStateOf(false) }
    val showGenderPickerDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.user_information_edit__header_title),
                onPopBack = {}
            )
        },
        bottomBar = {
            BottomBar(
                resourceId = R.string.user_information_edit__button_label,
                onClick = {}
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            if (showBirthPickerDialog.value) {
                BirthPickerDialog(
                    yearListState = rememberLazyListState(
                        editModel.birth.currentYearIndex()
                    ),
                    monthListState = rememberLazyListState(
                        editModel.birth.currentMonthIndex()
                    ),
                    yearOgions = editModel.birth.yearOgions(),
                    monthOgions = editModel.birth.monthOgions(),
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
                    listState = rememberLazyListState(currentGenderIndex),
                    ogions = genders,
                    onButtonClick = { genderIndex ->
                        showGenderPickerDialog.value = false
                    },
                    onDismissRequest = {
                        showGenderPickerDialog.value = false
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            BirthBlock(
                text = DateUtil.toText(
                    year = editModel.birth.year,
                    month = editModel.birth.month,
                    pattern = stringResource(R.string.common__birth_format)
                ),
                onTextFieldClick = {
                    showBirthPickerDialog.value = true
                },
                interactionSource = interactionSource
            )
            Spacer(modifier = Modifier.height(16.dp))

            GenderBlock(
                text = editModel.gender,
                onTextFieldClick = {
                    showGenderPickerDialog.value = true
                },
                interactionSource = interactionSource
            )

            if (editModel.isJapanCountryCode) {
                Spacer(modifier = Modifier.height(16.dp))

                ZipBlock(
                    text = editModel.zip,
                    onSearchButtonClick = {},
                    onValueChange = {},
                    interactionSource = interactionSource
                )

                Spacer(modifier = Modifier.height(16.dp))

                AddressBlock(
                    text = editModel.address,
                    interactionSource = interactionSource
                )
            }
        }
    }
}
