package com.example.luminarytrading.api

import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoService {
    @GET("simple/price")
    suspend fun getPrices(
        @Query("ids") ids: String,
        @Query("vs_currencies") vsCurrencies: String = "usd",
        @Query("include_24hr_change") include24hrChange: Boolean = true
    ): Map<String, CoinPriceResponse>
}

data class CoinPriceResponse(
    val usd: Double,
    val usd_24h_change: Double
) 