package com.thesetox.transaction

/**
 * Simple in-memory implementation of [TransactionRepository].
 */
class TransactionDataRepository : TransactionRepository {
    private val list = mutableListOf<Transaction>()

    override fun saveTransaction(transaction: Transaction) {
        list.add(transaction)
    }

    override fun getTransactions(): List<Transaction> = list.toList()
}
