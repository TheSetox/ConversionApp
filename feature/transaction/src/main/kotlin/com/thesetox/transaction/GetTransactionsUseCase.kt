package com.thesetox.transaction

/**
 * Returns the list of saved transactions.
 */
class GetTransactionsUseCase(private val repository: TransactionRepository) {
    operator fun invoke(): List<Transaction> = repository.getTransactions()
}
