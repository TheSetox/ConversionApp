package com.thesetox.exchange

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thesetox.databse.CurrencyRateEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExchangeViewModel(
    getListOfCurrencyRate: GetListOfCurrencyRateUseCase,
) : ViewModel() {
    val state: StateFlow<List<CurrencyRateEntity>> =
        getListOfCurrencyRate().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList(),
        )

    fun onSellValueChanged(value: String) {
        viewModelScope.launch {
            Log.d(TAG, "onSellValueChanged: $value")
        }
    }

    fun onReceiveValueChanged(value: String) {
        viewModelScope.launch {
            Log.d(TAG, "onReceiveValueChanged: $value")
        }
    }

    fun onSubmit() {
        viewModelScope.launch {
            Log.d(TAG, "onSubmit")
        }
    }

    companion object {
        private val TAG = ExchangeViewModel::class.java.simpleName
    }
}
