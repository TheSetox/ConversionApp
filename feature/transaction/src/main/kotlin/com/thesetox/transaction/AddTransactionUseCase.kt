package com.thesetox.transaction

/**
 * Saves a transaction using [TransactionRepository].
 */
class AddTransactionUseCase(private val repository: TransactionRepository) {
    operator fun invoke(transaction: Transaction) {
        repository.saveTransaction(transaction)
    }
}
