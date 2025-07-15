package com.io.tea.android.ui.common.component.model

import androidx.annotation.StringRes
import com.io.tea.android.R

data class PermissionAlertModel(
    @StringRes val titleId: Int,
    @StringRes val descriptionId: Int,
    @StringRes val confirmButtonId: Int,
    @StringRes val dismissButtonId: Int?,
    val onConfirmClick: () -> Unit = {},
    val onDismissClick: () -> Unit = {},
) {
    companion object {
        val Rationale = PermissionAlertModel(
            titleId = R.string.permission_dialog_rationale__title,
            descriptionId = R.string.permission_dialog_rationale__description,
            confirmButtonId = R.string.permission_dialog_rationale__confirm,
            dismissButtonId = null,
        )

        val Denied = PermissionAlertModel(
            titleId = R.string.permission_dialog_denied__title,
            descriptionId = R.string.permission_dialog_denied__description,
            confirmButtonId = R.string.permission_dialog_denied__confirm,
            dismissButtonId = R.string.permission_dialog_denied__dismiss,
        )
    }
}
