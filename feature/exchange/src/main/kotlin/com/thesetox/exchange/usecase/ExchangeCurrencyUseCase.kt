package com.thesetox.exchange.usecase

import com.thesetox.exchange.model.Balance
import com.thesetox.exchange.model.ExchangeResult
import com.thesetox.exchange.model.ExchangeResultWithUpdatedBalances

class ExchangeCurrencyUseCase(private val getCommission: GetCommissionUseCase) {
    operator fun invoke(
        sellAmount: String,
        receiveAmount: String,
        selectedSellCurrency: String,
        selectedReceiveCurrency: String,
        currentBalances: List<Balance>,
    ): ExchangeResultWithUpdatedBalances {
        val sell = sellAmount.toDoubleOrNull()
        val receive = receiveAmount.toDoubleOrNull()

        if (sell == null || receive == null) {
            return ExchangeResultWithUpdatedBalances(
                result = ExchangeResult.Error("Invalid amount"),
                updatedBalances = currentBalances,
            )
        }

        val balances = currentBalances.associateBy { it.code }.toMutableMap()

        val sellBalance =
            balances[selectedSellCurrency]?.value
                ?: return ExchangeResultWithUpdatedBalances(
                    result = ExchangeResult.Error("No $selectedSellCurrency balance"),
                    updatedBalances = currentBalances,
                )

        val commission = getCommission(sell, selectedSellCurrency)
        val totalToDeduct = sell + commission

        if (sellBalance < totalToDeduct) {
            return ExchangeResultWithUpdatedBalances(
                result =
                    ExchangeResult.Error(
                        "Insufficient $selectedSellCurrency balance. Required: $totalToDeduct",
                    ),
                updatedBalances = currentBalances,
            )
        }

        val updatedSell = sellBalance - totalToDeduct
        val updatedReceive = balances[selectedReceiveCurrency]?.value?.plus(receive) ?: receive

        balances[selectedSellCurrency] = Balance(selectedSellCurrency, updatedSell)
        balances[selectedReceiveCurrency] = Balance(selectedReceiveCurrency, updatedReceive)

        return ExchangeResultWithUpdatedBalances(
            result = ExchangeResult.Success(commission.toString()),
            updatedBalances = balances.values.toList(),
        )
    }
}
