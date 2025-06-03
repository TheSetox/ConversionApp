package com.thesetox.exchange.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thesetox.exchange.model.ExchangeEffect
import com.thesetox.exchange.model.ExchangeResult
import com.thesetox.exchange.model.ExchangeResultWithUpdatedBalances
import com.thesetox.exchange.model.ExchangeState
import com.thesetox.exchange.usecase.ConvertCurrencyUseCase
import com.thesetox.exchange.usecase.ExchangeCurrencyUseCase
import com.thesetox.exchange.usecase.GetDefaultBalanceUseCase
import com.thesetox.exchange.usecase.GetListOfCurrencyUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExchangeViewModel(
    private val getListOfCurrency: GetListOfCurrencyUseCase,
    private val getDefaultBalance: GetDefaultBalanceUseCase,
    private val convertCurrency: ConvertCurrencyUseCase,
    private val exchangeCurrency: ExchangeCurrencyUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<ExchangeState>(ExchangeState())
    val state: StateFlow<ExchangeState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ExchangeEffect>()
    val effect: SharedFlow<ExchangeEffect> = _effect.asSharedFlow()

    init {
        getMapOfBalance()
        getListOfCurrencies()
    }

    private fun getMapOfBalance() {
        _state.update { it.copy(listOfBalance = getDefaultBalance()) }
    }

    private fun getListOfCurrencies() {
        viewModelScope.launch {
            getListOfCurrency().collect {
                _state.emit(_state.value.copy(listOfCurrencies = it))
            }
        }
    }

    fun onSellValueChanged(value: String) {
        Log.d(TAG, "onSellValueChanged: $value")
        _state.update { it.copy(sellAmount = value) }

        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    receiveAmount =
                        convertCurrency(
                            amount = value,
                            toCurrency = _state.value.selectedReceiveCurrency,
                            fromCurrency = _state.value.selectedSellCurrency,
                        ),
                ),
            )
        }
    }

    fun onReceiveValueChanged(value: String) {
        Log.d(TAG, "onReceiveValueChanged: $value")
        _state.update { it.copy(receiveAmount = value) }
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    sellAmount =
                        convertCurrency(
                            amount = value,
                            toCurrency = _state.value.selectedSellCurrency,
                            fromCurrency = _state.value.selectedReceiveCurrency,
                        ),
                ),
            )
        }
    }

    fun onSelectedSellCurrency(selected: String) {
        viewModelScope.launch {
            val currentState = _state.value
            _state.emit(
                currentState.copy(
                    selectedSellCurrency = selected,
                    sellAmount = currentState.sellAmount,
                    receiveAmount =
                        convertCurrency(
                            amount = currentState.sellAmount,
                            toCurrency = currentState.selectedReceiveCurrency,
                            fromCurrency = selected,
                        ),
                ),
            )
        }
    }

    fun onSelectedReceiveCurrency(selected: String) {
        viewModelScope.launch {
            val currentState = _state.value
            _state.emit(
                currentState.copy(
                    selectedReceiveCurrency = selected,
                    receiveAmount = currentState.receiveAmount,
                    sellAmount =
                        convertCurrency(
                            amount = currentState.receiveAmount,
                            toCurrency = currentState.selectedSellCurrency,
                            fromCurrency = selected,
                        ),
                ),
            )
        }
    }

    fun onSubmit() {
        Log.d(TAG, "onSubmit")
        val currentState = _state.value

        val exchangeResult: ExchangeResultWithUpdatedBalances =
            exchangeCurrency(
                sellAmount = currentState.sellAmount,
                receiveAmount = currentState.receiveAmount,
                selectedSellCurrency = currentState.selectedSellCurrency,
                selectedReceiveCurrency = currentState.selectedReceiveCurrency,
                currentBalances = currentState.listOfBalance,
            )

        val updatedBalances = exchangeResult.updatedBalances
        when (val result = exchangeResult.result) {
            is ExchangeResult.Success -> {
                _state.update { it.copy(listOfBalance = updatedBalances) }
                viewModelScope.launch {
                    val message =
                        "You have converted ${currentState.sellAmount} " +
                            "${currentState.selectedSellCurrency} to " +
                            "${currentState.receiveAmount} " +
                            "${currentState.selectedReceiveCurrency}. " +
                            "Commission Fee: ${result.commissionFee} " +
                            "${currentState.selectedSellCurrency}."
                    _effect.emit(ExchangeEffect.ShowDialog(message))
                }
            }

            is ExchangeResult.Error -> {
                Log.d(TAG, "onSubmitError ${result.message}")
                viewModelScope.launch {
                    _effect.emit(ExchangeEffect.ShowToastMessage(result.message))
                }
            }
        }
    }

    companion object {
        private val TAG = ExchangeViewModel::class.java.simpleName
    }
}
