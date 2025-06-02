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

    CurrencyExchangeScaffold(
        state = state,
        onSellValueChanged = { viewModel.onSellValueChanged(it) },
        onSellSpinnerClicked = { TODO("Show Bottom sheet") },
        onReceiveValueChanged = { viewModel.onReceiveValueChanged(it) },
        onReceiveSpinnerClicked = { TODO("Show Bottom sheet") },
        onSubmit = { viewModel.onSubmit() },
    )
}

@Composable
private fun CurrencyExchangeScaffold(
    state: ExchangeState = ExchangeState(),
    onSellValueChanged: (String) -> Unit = {},
    onSellSpinnerClicked: () -> Unit = {},
    onReceiveValueChanged: (String) -> Unit = {},
    onReceiveSpinnerClicked: () -> Unit = {},
    onSubmit: () -> Unit = {},
) {
    Scaffold(
        topBar = { ConversionTopBar(stringId = R.string.currency_converter_title) },
        content = {
            it.CurrencyExchangeContent(
                state = state,
                onSellValueChanged = onSellValueChanged,
                onSellSpinnerClicked = onSellSpinnerClicked,
                onReceiveValueChanged = onReceiveValueChanged,
                onReceiveSpinnerClicked = onReceiveSpinnerClicked,
                onSubmit = onSubmit,
            )
        },
    )
}

@Composable
private fun PaddingValues.CurrencyExchangeContent(
    state: ExchangeState = ExchangeState(),
    onSellValueChanged: (String) -> Unit = {},
    onSellSpinnerClicked: () -> Unit = {},
    onReceiveValueChanged: (String) -> Unit = {},
    onReceiveSpinnerClicked: () -> Unit = {},
    onSubmit: () -> Unit = {},
) {
    Column(
        modifier =
            Modifier
                .padding(top = calculateTopPadding())
                .padding(horizontal = 16.dp),
    ) {
        ConversionSpacer(16.dp)

        Text(stringResource(R.string.my_balance_title))
        ConversionSpacer(16.dp)

        BalanceLazyRow(listOfBalance = state.listOfBalance)
        ConversionSpacer(24.dp)

        Text(stringResource(R.string.currency_exchange_title))
        ConversionSpacer(16.dp)

        SellRow(
            value = state.sellAmount,
            currency = state.selectedSellCurrency,
            onValueChanged = onSellValueChanged,
            onSpinnerClicked = onSellSpinnerClicked,
        )
        ConversionSpacer(16.dp)

        ReceiveRow(
            value = state.receiveAmount,
            currency = state.selectedReceiveCurrency,
            onValueChanged = onReceiveValueChanged,
            onSpinnerClicked = onReceiveSpinnerClicked,
        )
        ConversionSpacer(24.dp)

        SubmitButton { onSubmit() }
    }
}

@Preview
@Composable
private fun CurrencyExchangeScreenPreview() = CurrencyExchangeScaffold()
