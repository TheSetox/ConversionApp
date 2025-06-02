package com.thesetox.exchange.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyBottomSheet(
    currencies: List<String>,
    onCurrencySelected: (String) -> Unit,
    onDismissRequest: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.fillMaxWidth(),
    ) {
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
        ) {
            items(currencies) { currency ->
                Text(
                    text = currency,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .clickable { onCurrencySelected(currency) }
                            .padding(16.dp),
                )
            }
        }
    }
}
