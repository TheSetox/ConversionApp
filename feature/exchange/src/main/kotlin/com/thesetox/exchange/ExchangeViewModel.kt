package com.thesetox.exchange

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thesetox.exchange.ui.ExchangeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExchangeViewModel(
    getListOfCurrencyRate: GetListOfCurrencyRateUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<ExchangeState>(ExchangeState())
    val state: StateFlow<ExchangeState> = _state

    fun onSellValueChanged(value: String) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(sellAmount = value))
            // TODO convert receive amount, pass selected currency.
            Log.d(TAG, "onSellValueChanged: $value")
        }
    }

    fun onReceiveValueChanged(value: String) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(receiveAmount = value))
            // TODO convert receive amount, pass selected currency.
            Log.d(TAG, "onReceiveValueChanged: $value")
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
