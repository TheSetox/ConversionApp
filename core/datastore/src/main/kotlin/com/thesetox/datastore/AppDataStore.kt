package com.thesetox.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

/**
 * Abstraction over Jetpack DataStore used to persist small pieces of
 * application state. Currently it stores the hash of the latest fetched
 * currency rates so features can determine when fresh data is available.
 */
interface AppDataStore {
    /**
     * Retrieve the previously saved currency rate hash.
     *
     * @return the stored hash or an empty string if no value was saved
     */
    suspend fun getCurrencyRateHash(): String

    /**
     * Persist the given currency rate hash.
     *
     * @param hash value to store
     */
    suspend fun saveCurrencyRateHash(hash: String)
}

/**
 * [AppDataStore] implementation backed by Jetpack [DataStore].
 *
 * @param dataStore underlying DataStore used for persistence
 */
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
        /** Preference key used to store the currency rate hash. */
        private val HASH_KEY = stringPreferencesKey("HASH_KEY")
    }
}
