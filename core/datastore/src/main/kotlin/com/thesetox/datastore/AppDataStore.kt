package com.thesetox.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

interface AppDataStore {
    suspend fun getCurrencyRateHash(): String

    suspend fun saveCurrencyRateHash(hash: String)
}

class LocalDataSource(private val dataStore: DataStore<Preferences>) : AppDataStore {
    override suspend fun getCurrencyRateHash(): String {
        val preferences = dataStore.data.first()
        val hash = preferences[HASH_KEY]
        return hash.orEmpty()
    }

    override suspend fun saveCurrencyRateHash(hash: String) {
        dataStore.edit { preferences ->
            preferences[HASH_KEY] = hash
        }
    }

    companion object {
        private val HASH_KEY = stringPreferencesKey("HASH_KEY")
    }
}
