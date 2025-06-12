package com.example.luminarytrading.Activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.luminarytrading.Adapter.CryptoAdapter
import com.example.luminarytrading.Adapter.StockAdapter
import com.example.luminarytrading.Model.CryptoModel
import com.example.luminarytrading.Model.Model
import com.example.luminarytrading.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val lineData = arrayListOf(1000, 1100, 1200, 1100)
    private val lineData2 = arrayListOf(2100, 2000, 1900, 2000)
    private val lineData3 = arrayListOf(900, 1100, 1200, 1000, 1150)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS

        )

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.WHITE
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE

        CryptoList()
        StockList()

    }

    private fun StockList() {
        binding.stockView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val domainArrayList = arrayListOf(
            Model("NASDAQ100", "BTC", 110000.11, 2.1, lineData, 1.1, 0.1),
            Model("S&P 500", "ETH", 2400.1, 5.2, lineData2, 1.1, 0.1),
            Model("Dow Jones", "TRX", 661.16, 0.68, lineData3, 1.1, 0.1)
        )

        binding.stockView.adapter = StockAdapter(domainArrayList)
    }

    private fun CryptoList() {
        binding.cryptoView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val domainArrayList = arrayListOf(
            CryptoModel(
                "Bitcoin", "BTC/USDT", 67890.12, 2.56,
                arrayListOf(45000, 47000, 49000, 51000, 53000, 55000, 57000),
                1.5, 101835.18,
                68000.0, 68100.0, 68200.0, 68300.0,
                0.1, 0.2, 0.15, 0.05,
                67850.0, 67700.0, 67550.0, 67400.0, 67250.0, 67100.0, 67000.0,
                0.3, 0.25, 0.2, 0.15, 0.1, 0.05, 0.03,
                66000.0, 67890.12, 68500.0, 65500.0, 1890.12, 12000.0,
                "bitcoin"
            ),
            CryptoModel(
                "Ethereum", "ETH/USDT", 3500.75, -1.34,
                arrayListOf(3200, 3300, 3400, 3600, 3550, 3500, 3450),
                10.0, 35007.5,
                3510.0, 3520.0, 3530.0, 3540.0,
                1.0, 1.5, 2.0, 0.5,
                3490.0, 3480.0, 3470.0, 3460.0, 3450.0, 3440.0, 3430.0,
                2.5, 2.0, 1.8, 1.2, 0.9, 0.6, 0.3,
                3600.0, 3500.75, 3620.0, 3480.0, -99.25, 8000.0,
                "ethereum"
            ),
            CryptoModel(
                "Tron", "TRX", 0.1234, 0.87,
                arrayListOf(110, 115, 120, 122, 124, 125, 127),
                5000.0, 617.0,
                0.1240, 0.1250, 0.1260, 0.1270,
                1000.0, 800.0, 600.0, 400.0,
                0.1230, 0.1225, 0.1220, 0.1215, 0.1210, 0.1205, 0.1200,
                1200.0, 1100.0, 1000.0, 900.0, 800.0, 700.0, 600.0,
                0.12, 0.1234, 0.125, 0.118, 0.0034, 30000000.0,
                "tron"
            )
        )
        binding.cryptoView.adapter = CryptoAdapter(domainArrayList)

    }
}