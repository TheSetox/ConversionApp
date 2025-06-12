package com.thesetox.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "balance")
data class BalanceEntity(
    @PrimaryKey val code: String = "",
    val value: Double = 0.0,
)
