package com.thesetox.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Koin module that provides networking dependencies for the application.
 *
 * It exposes:
 * - a configured [Json] instance used for Kotlinx serialization
 * - a [HttpClient] with the `ContentNegotiation` plugin
 * - the [CurrencyRateApi] implementation backed by [CurrencyRateRemoteSource]
 */
val networkModule =
    module {
        // Provide the Json serializer configured to ignore unknown fields.
        single<Json> {
            Json {
                ignoreUnknownKeys = true
            }
        }

        // Create a Ktor HttpClient that uses the provided Json configuration.
        single<HttpClient> {
            HttpClient {
                install(ContentNegotiation) {
                    json(get())
                }
            }
        }

        // Bind the remote source as the implementation for CurrencyRateApi.
        singleOf(::CurrencyRateRemoteSource) { bind<CurrencyRateApi>() }
    }
