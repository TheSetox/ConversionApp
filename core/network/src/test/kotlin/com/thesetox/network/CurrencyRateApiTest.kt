package com.thesetox.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CurrencyRateApiTest {
    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `fetchCurrencyRates returns success`() =
        runTest {
            // Arrange
            val mockEngine =
                MockEngine {
                    respond(
                        content = """{"base":"EUR","date":"2020-01-01","rates":{"USD":1.2}}""",
                        status = HttpStatusCode.OK,
                        headers = headersOf("Content-Type", "application/json"),
                    )
                }
            val client =
                HttpClient(mockEngine) {
                    install(ContentNegotiation) { json(json) }
                }
            val api = CurrencyRateRemoteSource(client)

            // Act
            val result = api.fetchCurrencyRates()

            // Assert
            assertTrue(result is ApiResult.Success)
        }

    @Test
    fun `fetchCurrencyRates returns error for non-successful response`() =
        runTest {
            // Arrange
            val mockEngine = MockEngine { respondError(HttpStatusCode.InternalServerError) }
            val client =
                HttpClient(mockEngine) {
                    install(ContentNegotiation) { json(json) }
                }
            val api = CurrencyRateRemoteSource(client)

            // Act
            val result = api.fetchCurrencyRates()

            // Assert
            assertTrue(result is ApiResult.Error)
        }

    @Test
    fun `fetchCurrencyRates uses correct endpoint`() =
        runTest {
            // Arrange
            var requestedUrl = ""
            val mockEngine =
                MockEngine { request ->
                    requestedUrl = request.url.toString()
                    respond(
                        content = """{"base":"EUR","date":"2020-01-01","rates":{"USD":1.2}}""",
                        status = HttpStatusCode.OK,
                        headers = headersOf("Content-Type", "application/json"),
                    )
                }
            val client =
                HttpClient(mockEngine) {
                    install(ContentNegotiation) { json(json) }
                }
            val api = CurrencyRateRemoteSource(client)

            // Act
            api.fetchCurrencyRates()

            // Assert
            assertEquals(
                "https://developers.paysera.com/tasks/api/currency-exchange-rates",
                requestedUrl,
            )
        }
}
