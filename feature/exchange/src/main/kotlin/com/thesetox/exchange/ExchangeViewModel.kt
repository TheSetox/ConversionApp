package com.thesetox.exchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thesetox.databse.CurrencyRateEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ExchangeViewModel(getListOfCurrencyRate: GetListOfCurrencyRateUseCase) : ViewModel() {
    val state: StateFlow<List<CurrencyRateEntity>> =
        getListOfCurrencyRate().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList(),
        )
}
