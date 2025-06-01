package com.thesetox.exchange

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thesetox.databse.CurrencyRateEntity
import com.thesetox.designsystem.ConversionTopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun CurrencyExchangeScreen(viewModel: ExchangeViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CurrencyExchangeScaffold(state)
}

@Composable
private fun CurrencyExchangeScaffold(state: List<CurrencyRateEntity> = emptyList()) {
    Scaffold(
        topBar = { ConversionTopBar(stringId = R.string.currency_exchange_title) },
        content = { it.CurrencyExchangeContent(state) },
    )
}

@Composable
private fun PaddingValues.CurrencyExchangeContent(state: List<CurrencyRateEntity>) {
    Box(modifier = Modifier.padding(this)) {
    }
}

@Preview
@Composable
private fun CurrencyExchangeScreenPreview() = CurrencyExchangeScaffold()
