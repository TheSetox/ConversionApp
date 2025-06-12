package com.thesetox.transaction

/**
 * Repository for persisting and retrieving transactions.
 */
interface TransactionRepository {
    /** Adds a new [Transaction] to the history. */
    fun saveTransaction(transaction: Transaction)

    /** Returns all recorded transactions. */
    fun getTransactions(): List<Transaction>
}
