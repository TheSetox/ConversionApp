package com.thesetox.exchange.usecase

import com.thesetox.exchange.model.Balance
import com.thesetox.exchange.model.ExchangeResult
import com.thesetox.exchange.model.ExchangeResultWithUpdatedBalances

class ExchangeCurrencyUseCase {
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
            balances[selectedSellCurrency]?.value ?: return ExchangeResultWithUpdatedBalances(
                result = ExchangeResult.Error("No $selectedSellCurrency balance"),
                updatedBalances = currentBalances,
            )

        if (sellBalance <= sell) {
            return ExchangeResultWithUpdatedBalances(
                result =
                    ExchangeResult.Error(
                        "Insufficient $selectedSellCurrency balance, " +
                            "amount should not be equal or greater than the balance",
                    ),
                updatedBalances = currentBalances,
            )
        }

        val updatedSell = sellBalance - sell
        val updatedReceive = balances[selectedReceiveCurrency]?.value?.plus(receive) ?: receive

        balances[selectedSellCurrency] = Balance(selectedSellCurrency, updatedSell)
        balances[selectedReceiveCurrency] = Balance(selectedReceiveCurrency, updatedReceive)

        return ExchangeResultWithUpdatedBalances(
            result = ExchangeResult.Success,
            updatedBalances = balances.values.toList(),
        )
    }
}
