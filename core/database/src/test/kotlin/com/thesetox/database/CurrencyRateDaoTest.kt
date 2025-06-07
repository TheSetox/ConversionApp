package com.thesetox.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class CurrencyRateDaoTest {
    private lateinit var database: ConversionAppDatabase
    private lateinit var dao: CurrencyRateDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        database =
            Room.inMemoryDatabaseBuilder(
                context,
                ConversionAppDatabase::class.java,
            ).allowMainThreadQueries().build()
        dao = database.currencyRateDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `insertRates increases list size`() =
        runTest {
            // Arrange
            val rates =
                listOf(
                    CurrencyRateEntity("USD", 1.1, "2024-01-01"),
                    CurrencyRateEntity("GBP", 0.9, "2024-01-01"),
                )

            // Act
            dao.insertRates(rates)

            // Assert
            val list = dao.getCurrencyRateList().first()
            assertEquals(2, list.size)
        }

    @Test
    fun `getCurrencyRateList returns inserted item`() =
        runTest {
            // Arrange
            val rate = CurrencyRateEntity("USD", 1.1, "2024-01-01")
            dao.insertRates(listOf(rate))

            // Act
            val firstRate = dao.getCurrencyRateList().first().first()

            // Assert
            assertEquals("USD", firstRate.currencyCode)
        }

    @Test
    fun `clearRates deletes all entries`() =
        runTest {
            // Arrange
            dao.insertRates(listOf(CurrencyRateEntity("USD", 1.1)))

            // Act
            dao.clearRates()

            // Assert
            val list = dao.getCurrencyRateList().first()
            assertEquals(0, list.size)
        }

    @Test
    fun `getRate returns null when rate is absent`() =
        runTest {
            // Act
            val result = dao.getRate("EUR")

            // Assert
            assertNull(result)
        }
}
