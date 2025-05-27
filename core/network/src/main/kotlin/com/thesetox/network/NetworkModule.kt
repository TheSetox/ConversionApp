package com.thesetox.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule =
    module {
        single<Json> {
            Json {
                ignoreUnknownKeys = true
            }
        }

        single<HttpClient> {
            HttpClient {
                install(ContentNegotiation) {
                    json(get())
                }
            }
        }

        single<CurrencyRateApi> { CurrencyRateApi(get()) }
    }
