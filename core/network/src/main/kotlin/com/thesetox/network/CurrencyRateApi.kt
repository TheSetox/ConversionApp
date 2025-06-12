package com.thesetox.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Defines the contract for retrieving currency exchange rates from the remote
 * service.
 */
interface CurrencyRateApi {
    /**
     * Fetch the latest currency rates.
     *
     * @return an [ApiResult] wrapping the [CurrencyRateResponse] or an error
     *   describing what went wrong.
     */
    suspend fun fetchCurrencyRates(): ApiResult<CurrencyRateResponse>
}

/**
 * Implementation of [CurrencyRateApi] backed by a Ktor [HttpClient].
 *
 * @param client HTTP client used to make requests.
 */
class CurrencyRateRemoteSource(private val client: HttpClient) : CurrencyRateApi {
    override suspend fun fetchCurrencyRates(): ApiResult<CurrencyRateResponse> {
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
    val api = CurrencyRateRemoteSource(client)
    print(api.fetchCurrencyRates())
}
