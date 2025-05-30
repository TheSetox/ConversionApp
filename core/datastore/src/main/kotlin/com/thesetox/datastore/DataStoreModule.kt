package com.thesetox.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataStoreModule =
    module {
        single<DataStore<Preferences>> { androidContext().dataStore }

        singleOf(::LocalDataSource) { bind<AppDataStore>() }
    }

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "secure_prefs")
