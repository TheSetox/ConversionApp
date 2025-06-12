/**
 * Provides dependencies related to Jetpack DataStore.
 *
 * The [dataStoreModule] exposes a single [androidx.datastore.core.DataStore] of
 * [androidx.datastore.preferences.core.Preferences] named `"secure_prefs"`. It
 * also binds the [AppDataStore] interface to its implementation [LocalDataSource]
 * so other modules can persist small pieces of data without knowing the
 * underlying storage mechanism.
 */

package com.thesetox.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Koin module providing a DataStore instance and its [AppDataStore]
 * implementation.
 */
val dataStoreModule =
    module {
        single<DataStore<Preferences>> { androidContext().dataStore }

        singleOf(::LocalDataSource) { bind<AppDataStore>() }
    }

/**
 * Lazily creates the application's [DataStore] named `"secure_prefs"`.
 */
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "secure_prefs")
