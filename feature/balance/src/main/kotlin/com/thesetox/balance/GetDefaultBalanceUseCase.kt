package com.thesetox.balance

/**
 * Use case providing the application's initial wallet balance.
 *
 * Wrapping the default balance in a dedicated use case keeps the creation
 * logic in a single place and allows other modules to obtain the starting
 * wallet through dependency injection. Currently the user starts with a
 * single entry of `1000 EUR`.
 */
class GetDefaultBalanceUseCase {
    /**
     * Returns a list containing the default balance entry.
     *
     * @return list with one [Balance] representing `1000 EUR`
     */
    operator fun invoke(): List<Balance> {
        return listOf(Balance(code = "EUR", value = 1000.0))
    }
}
