package com.thesetox.exchange.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.thesetox.exchange.ui.component.CurrencyBottomSheet
import com.thesetox.exchange.ui.component.ReceiveRow
import com.thesetox.exchange.ui.component.SellRow
import com.thesetox.exchange.ui.component.SubmitButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun CurrencyExchangeScreen(viewModel: ExchangeViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    var showCurrencySheet by remember { mutableStateOf(false) }
    var isSelectingSellCurrency by remember { mutableStateOf(true) }

    CurrencyExchangeScaffold(
        state = state,
        onSellValueChanged = { viewModel.onSellValueChanged(it) },
        onSellSpinnerClicked = {
            isSelectingSellCurrency = true
            showCurrencySheet = true
        },
        onReceiveValueChanged = { viewModel.onReceiveValueChanged(it) },
        onReceiveSpinnerClicked = {
            isSelectingSellCurrency = false
            showCurrencySheet = true
        },
        onSubmit = { viewModel.onSubmit() },
    )

    if (showCurrencySheet) {
        CurrencyBottomSheet(
            currencies = state.listOfCurrencies,
            onCurrencySelected = { selected ->
                if (isSelectingSellCurrency) {
                    viewModel.onSelectedSellCurrency(selected)
                } else {
                    viewModel.onSelectedReceiveCurrency(selected)
                }
                showCurrencySheet = false
            },
            onDismissRequest = { showCurrencySheet = false },
        )
    }
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
