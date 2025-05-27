package com.thesetox.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class CurrencyRateApi(private val client: HttpClient) {
    suspend fun fetchCurrencyRates(): ApiResult<CurrencyRateResponse> {
        val endpoint = "/tasks/api/currency-exchange-rates"
        val url = BASE_URL + endpoint
        return runCatching {
            client.get(url).body<CurrencyRateResponse>()
        }.fold(
            onSuccess = { ApiResult.Success(it) },
            onFailure = { ApiResult.Error(it.message.orEmpty(), it.cause) },
        )
    }

    companion object {
        private const val BASE_URL = "https://developers.paysera.com"
    }
}

/**
 * Test if the CurrencyRateApi can successfully call the API and return the exchange rates.
*/
suspend fun main() {
    val client =
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    },
                )
            }
        }
    val api = CurrencyRateApi(client)
    print(api.fetchCurrencyRates())
}
