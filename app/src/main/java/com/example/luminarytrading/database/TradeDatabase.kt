package com.example.luminarytrading.database

import androidx.room.*
import java.util.Date

@Entity(tableName = "trades")
data class Trade(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val cryptoId: String,
    val amount: Double,
    val price: Double,
    val type: TradeType,
    val timestamp: Date
)

enum class TradeType {
    BUY, SELL
}

@Dao
interface TradeDao {
    @Query("SELECT * FROM trades ORDER BY timestamp DESC")
    suspend fun getAllTrades(): List<Trade>

    @Query("SELECT * FROM trades WHERE cryptoId = :cryptoId ORDER BY timestamp DESC")
    suspend fun getTradesForCrypto(cryptoId: String): List<Trade>

    @Insert
    suspend fun insertTrade(trade: Trade)

    @Query("SELECT SUM(CASE WHEN type = 'BUY' THEN amount ELSE -amount END) FROM trades WHERE cryptoId = :cryptoId")
    suspend fun getCryptoBalance(cryptoId: String): Double?
}

@Database(entities = [Trade::class], version = 1)
@TypeConverters(Converters::class)
abstract class TradeDatabase : RoomDatabase() {
    abstract fun tradeDao(): TradeDao
}

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
} 