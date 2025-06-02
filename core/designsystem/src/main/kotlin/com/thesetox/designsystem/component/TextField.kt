package com.thesetox.designsystem.component

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.thesetox.designsystem.MediumTextStyle

@Composable
fun ConversionTextField(
    value: String = "",
    onValueChanged: (String) -> Unit = {},
) {
    BasicTextField(
        singleLine = true,
        textStyle = MediumTextStyle.copy(textAlign = TextAlign.End),
        value = value,
        onValueChange = onValueChanged,
    )
}

@Preview(showBackground = true)
@Composable
private fun ConversionTextFieldPreview() = ConversionTextField("100.00")
