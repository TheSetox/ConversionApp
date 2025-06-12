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
class BalanceDaoTest {
    private lateinit var database: ConversionAppDatabase
    private lateinit var dao: BalanceDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        database =
            Room.inMemoryDatabaseBuilder(
                context,
                ConversionAppDatabase::class.java,
            ).allowMainThreadQueries().build()
        dao = database.balanceDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `updateBalance inserts when new`() = runTest {
        // Act
        dao.updateBalance(BalanceEntity("EUR", 1000.0))

        // Assert
        val list = dao.getBalanceList().first()
        assertEquals(1, list.size)
        assertEquals("EUR", list.first().code)
    }

    @Test
    fun `updateBalance updates existing record`() = runTest {
        // Arrange
        dao.updateBalance(BalanceEntity("EUR", 1000.0))

        // Act
        dao.updateBalance(BalanceEntity("EUR", 2000.0))

        // Assert
        val balance = dao.getBalance("EUR")
        assertEquals(2000.0, balance?.value)
    }

    @Test
    fun `clearBalances deletes all entries`() = runTest {
        // Arrange
        dao.updateBalance(BalanceEntity("EUR", 1000.0))

        // Act
        dao.clearBalances()

        // Assert
        val list = dao.getBalanceList().first()
        assertEquals(0, list.size)
    }

    @Test
    fun `getBalance returns null when balance is absent`() = runTest {
        // Act
        val result = dao.getBalance("EUR")

        // Assert
        assertNull(result)
    }
}
