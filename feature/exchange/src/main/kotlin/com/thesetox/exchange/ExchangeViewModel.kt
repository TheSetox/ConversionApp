package com.thesetox.exchange

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thesetox.exchange.ui.ExchangeState
import com.thesetox.exchange.usecase.ConvertCurrencyUseCase
import com.thesetox.exchange.usecase.GetListOfCurrencyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExchangeViewModel(
    private val getListOfCurrency: GetListOfCurrencyUseCase,
    private val convertCurrency: ConvertCurrencyUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<ExchangeState>(ExchangeState())
    val state: StateFlow<ExchangeState> = _state.asStateFlow()

    init {
        getListOfCurrencies()
    }

    private fun getListOfCurrencies() {
        getListOfCurrency
        viewModelScope.launch {
            getListOfCurrency().collect {
                _state.emit(_state.value.copy(listOfCurrencies = it))
            }
        }
    }

    fun onSellValueChanged(value: String) {
        Log.d(TAG, "onSellValueChanged: $value")
        viewModelScope.launch {
            val currentState = _state.value
            if (currentState.sellAmount == value) return@launch
            _state.emit(
                currentState.copy(
                    sellAmount = value,
                    receiveAmount =
                        convertCurrency(
                            amount = value,
                            toCurrency = currentState.selectedReceiveCurrency,
                            fromCurrency = currentState.selectedSellCurrency,
                        ),
                ),
            )
        }
    }

    fun onReceiveValueChanged(value: String) {
        Log.d(TAG, "onReceiveValueChanged: $value")
        viewModelScope.launch {
            val currentState = _state.value
            if (currentState.receiveAmount == value) return@launch
            _state.emit(
                currentState.copy(
                    receiveAmount = value,
                    sellAmount =
                        convertCurrency(
                            amount = value,
                            toCurrency = currentState.selectedSellCurrency,
                            fromCurrency = currentState.selectedReceiveCurrency,
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
        viewModelScope.launch {
            // TODO check commission rates and get sellAmount and receiveAmount.
            // Also get the selected currencies. Deduct and add balance.
            Log.d(TAG, "onSubmit")
        }
    }

    companion object {
        private val TAG = ExchangeViewModel::class.java.simpleName
    }
}
