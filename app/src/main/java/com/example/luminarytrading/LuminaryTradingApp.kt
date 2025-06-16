package com.example.luminarytrading

import android.app.Application
import androidx.room.Room
import com.example.luminarytrading.api.CoinGeckoService
import com.example.luminarytrading.database.TradeDatabase
import com.example.luminarytrading.repository.CryptoRepository
import com.example.luminarytrading.viewmodel.CryptoViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LuminaryTradingApp : Application() {
    private lateinit var database: TradeDatabase
    private lateinit var api: CoinGeckoService
    private lateinit var repository: CryptoRepository
    lateinit var viewModel: CryptoViewModel

    override fun onCreate() {
        super.onCreate()
        
        // Initialize database
        database = Room.databaseBuilder(
            applicationContext,
            TradeDatabase::class.java,
            "trades_database"
        ).build()

        // Initialize API
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        api = Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinGeckoService::class.java)

        // Initialize repository
        repository = CryptoRepository(api, database.tradeDao())

        // Initialize ViewModel
        viewModel = CryptoViewModel(repository)
    }
} 