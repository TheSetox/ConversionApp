package com.thesetox.exchange.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thesetox.designsystem.MediumTextStyle
import com.thesetox.designsystem.Teal
import com.thesetox.designsystem.component.ConversionSpacer
import com.thesetox.designsystem.component.ConversionTextField
import com.thesetox.exchange.R

@Composable
fun ConversionRow(
    leadIcon: @Composable () -> Unit = {},
    titleId: Int,
    value: String = "",
    currency: String = "",
    onValueChanged: (String) -> Unit = {},
    onSpinnerClicked: () -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadIcon()
        ConversionSpacer(16.dp)
        Text(
            text = stringResource(titleId),
            style = MediumTextStyle,
        )
        Spacer(modifier = Modifier.weight(1f))
        ConversionTextField(value = value, onValueChanged = onValueChanged)
        ConversionSpacer(16.dp)
        CurrencySpinner(currency = currency, onClick = onSpinnerClicked)
    }
}

@Composable
fun SellRow(
    value: String = "",
    currency: String = "",
    onValueChanged: (String) -> Unit = {},
    onSpinnerClicked: () -> Unit = {},
) {
    ConversionRow(
        leadIcon = {
            LeadingIcon(color = Color.Red, drawable = R.drawable.arrow_upward)
        },
        titleId = R.string.sell_label,
        value = value,
        currency = currency,
        onValueChanged = onValueChanged,
        onSpinnerClicked = onSpinnerClicked,
    )
}

@Composable
fun ReceiveRow(
    value: String = "",
    currency: String = "",
    onValueChanged: (String) -> Unit = {},
    onSpinnerClicked: () -> Unit = {},
) {
    ConversionRow(
        leadIcon = {
            LeadingIcon(color = Teal, drawable = R.drawable.arrow_downward)
        },
        titleId = R.string.receive_label,
        value = value,
        currency = currency,
        onValueChanged = onValueChanged,
        onSpinnerClicked = onSpinnerClicked,
    )
}

@Preview(showBackground = true)
@Composable
private fun SellRowPreview() = SellRow()

@Preview(showBackground = true)
@Composable
private fun ReceiveRowPreview() = ReceiveRow()

@Preview(showBackground = true)
@Composable
private fun ConversionRowPreview() =
    ConversionRow(
        leadIcon = { LeadingIcon(color = Color.Red, drawable = R.drawable.arrow_upward) },
        titleId = R.string.sell_label,
        value = "100",
        currency = "EUR",
    )
