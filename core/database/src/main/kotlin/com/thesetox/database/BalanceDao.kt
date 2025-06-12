package com.thesetox.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BalanceDao {
    @Query("SELECT * FROM balance")
    fun getBalanceList(): Flow<List<BalanceEntity>>

    @Query("SELECT * FROM balance WHERE code = :code")
    suspend fun getBalance(code: String): BalanceEntity?

    @Upsert
    suspend fun updateBalance(balance: BalanceEntity)

    @Query("DELETE FROM balance")
    suspend fun clearBalances()
}
