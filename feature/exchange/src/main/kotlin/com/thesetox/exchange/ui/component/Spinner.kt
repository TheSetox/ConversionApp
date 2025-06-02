package com.thesetox.exchange.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thesetox.designsystem.MediumTextStyle

@Composable
fun CurrencySpinner(
    currency: String,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = currency, style = MediumTextStyle)
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            modifier = Modifier.size(35.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencySpinnerPreview() = CurrencySpinner("EUR")
