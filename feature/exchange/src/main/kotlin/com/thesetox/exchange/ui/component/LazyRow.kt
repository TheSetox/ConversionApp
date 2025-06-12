package com.thesetox.exchange.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thesetox.balance.Balance
import com.thesetox.designsystem.MediumTextStyle

@Composable
fun BalanceLazyRow(listOfBalance: List<Balance>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(36.dp)) {
        items(listOfBalance) {
            Text(text = "${it.value} ${it.code}", style = MediumTextStyle)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BalanceLazyRowPreview() =
    BalanceLazyRow(
        listOf(
            Balance(code = "EUR", value = 1000.0),
            Balance(code = "USD", value = 100.0),
        ),
    )
