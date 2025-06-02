package com.thesetox.designsystem.component

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.thesetox.designsystem.MediumTextStyle

@Composable
fun ConversionTextField(
    value: String = "100.00",
    onValueChanged: (String) -> Unit = {},
) {
    BasicTextField(
        singleLine = true,
        textStyle = MediumTextStyle.copy(textAlign = TextAlign.End),
        value = value,
        onValueChange = onValueChanged,
    )
}
