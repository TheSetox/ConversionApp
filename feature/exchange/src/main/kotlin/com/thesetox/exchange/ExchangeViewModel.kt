package com.thesetox.exchange

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thesetox.exchange.ui.ExchangeState
import com.thesetox.exchange.usecase.ConvertCurrencyUseCase
import com.thesetox.exchange.usecase.GetListOfCurrencyRateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExchangeViewModel(
    private val getListOfCurrencyRate: GetListOfCurrencyRateUseCase,
    private val convertCurrency: ConvertCurrencyUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<ExchangeState>(ExchangeState())
    val state: StateFlow<ExchangeState> = _state.asStateFlow()

    fun onSellValueChanged(value: String) {
        Log.d(TAG, "onSellValueChanged: $value")
        viewModelScope.launch {
            val currentState = _state.value
            if (currentState.sellAmount == value) return@launch // 👈 Prevent redundant emit
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
            if (currentState.sellAmount == value) return@launch
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
