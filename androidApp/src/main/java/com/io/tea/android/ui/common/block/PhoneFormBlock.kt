package com.io.tea.android.ui.common.block

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.CountryCodeField
import com.io.tea.android.ui.common.PhoneNumberField

@SuppressLint("ModifierParameter")
@Composable
internal fun PhoneFormBlock(
    countryCode: String,
    interactionSource: MutableInteractionSource,
    phoneNumber: String,
    isError: Boolean = false,
    modifier: Modifier = Modifier,
    onCountryCodeFieldClick: () -> Unit,
    onCountrySelected: (String) -> Unit,
    onPhoneNumberChanged: (String) -> Unit,
) {
    CountryCodeField(
        text = countryCode,
        interactionSource = interactionSource,
        onTextFieldClick = onCountryCodeFieldClick,
        onCountrySelected = onCountrySelected,
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(21.dp))

    PhoneNumberField(
        phoneNumber = phoneNumber,
        interactionSource = interactionSource,
        isError = isError,
        onValueChange = onPhoneNumberChanged,
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(21.dp))
}

@SuppressLint("ModifierParameter")
@Composable
internal fun PhoneFormCountryCodeTextBlock(
    countryCode: String,
    interactionSource: MutableInteractionSource,
    phoneNumber: String = "",
    onPhoneNumberChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = stringResource(R.string.phone_number_input__country_code),
            style = TeaAppTheme.typography.label,
            color = TeaAppTheme.colors.FontSecondary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = countryCode,
            style = TeaAppTheme.typography.h4,
            modifier = Modifier
                .padding(vertical = 6.dp)
        )
    }

    Spacer(modifier = Modifier.height(13.dp))

    PhoneNumberField(
        phoneNumber = phoneNumber,
        interactionSource = interactionSource,
        onValueChange = onPhoneNumberChanged,
        modifier = modifier,
    )
    Spacer(modifier = Modifier.height(21.dp))
}

