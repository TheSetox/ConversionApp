package com.thesetox.exchange.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thesetox.databse.CurrencyRateEntity
import com.thesetox.designsystem.component.ConversionSpacer
import com.thesetox.designsystem.component.ConversionTopBar
import com.thesetox.exchange.ExchangeViewModel
import com.thesetox.exchange.R
import com.thesetox.exchange.ui.component.BalanceLazyRow
import com.thesetox.exchange.ui.component.ReceiveRow
import com.thesetox.exchange.ui.component.SellRow
import com.thesetox.exchange.ui.component.SubmitButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun CurrencyExchangeScreen(viewModel: ExchangeViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CurrencyExchangeScaffold(state)
}

@Composable
private fun CurrencyExchangeScaffold(state: List<CurrencyRateEntity> = emptyList()) {
    Scaffold(
        topBar = { ConversionTopBar(stringId = R.string.currency_converter_title) },
        content = { it.CurrencyExchangeContent(state) },
    )
}

@Composable
private fun PaddingValues.CurrencyExchangeContent(state: List<CurrencyRateEntity>) {
    Column(
        modifier =
            Modifier
                .padding(top = calculateTopPadding())
                .padding(horizontal = 16.dp),
    ) {
        ConversionSpacer(16.dp)

        Text(stringResource(R.string.my_balance_title))
        ConversionSpacer(16.dp)

        BalanceLazyRow()
        ConversionSpacer(24.dp)

        Text(stringResource(R.string.currency_exchange_title))
        ConversionSpacer(16.dp)

        SellRow()
        ConversionSpacer(16.dp)

        ReceiveRow()
        ConversionSpacer(24.dp)

        SubmitButton()
    }
}

@Preview
@Composable
private fun CurrencyExchangeScreenPreview() = CurrencyExchangeScaffold()
