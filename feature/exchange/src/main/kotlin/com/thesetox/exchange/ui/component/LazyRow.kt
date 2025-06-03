package com.thesetox.exchange.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
