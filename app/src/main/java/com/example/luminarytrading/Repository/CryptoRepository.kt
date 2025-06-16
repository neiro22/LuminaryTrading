package com.example.luminarytrading.repository

import com.example.luminarytrading.api.CoinGeckoService
import com.example.luminarytrading.database.Trade
import com.example.luminarytrading.database.TradeDao
import com.example.luminarytrading.database.TradeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date

class CryptoRepository(
    private val api: CoinGeckoService,
    private val tradeDao: TradeDao
) {
    suspend fun getCryptoPrices(ids: List<String>): Flow<Map<String, Double>> = flow {
        try {
            val response = api.getPrices(ids.joinToString(","))
            emit(response.mapValues { it.value.usd })
        } catch (e: Exception) {
            // Handle error
            throw e
        }
    }

    suspend fun addTrade(cryptoId: String, amount: Double, price: Double, type: TradeType) {
        val trade = Trade(
            cryptoId = cryptoId,
            amount = amount,
            price = price,
            type = type,
            timestamp = Date()
        )
        tradeDao.insertTrade(trade)
    }

    suspend fun getCryptoBalance(cryptoId: String): Double {
        return tradeDao.getCryptoBalance(cryptoId) ?: 0.0
    }

    suspend fun getTradesForCrypto(cryptoId: String): List<Trade> {
        return tradeDao.getTradesForCrypto(cryptoId)
    }
} 