package com.thesetox.exchange

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun CurrencyExchangeScreen(viewModel: ExchangeViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Log.d("CurrencyExchangeScreen", "CurrencyExchangeScreen: $state")
}

@Preview
@Composable
private fun CurrencyExchangeScreenPreview() = CurrencyExchangeScreen()
