package com.example.luminarytrading.Activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.luminarytrading.Adapter.CryptoAdapter
import com.example.luminarytrading.Model.CryptoModel
import com.example.luminarytrading.databinding.ActivityMainBinding
import com.example.luminarytrading.R
import com.example.luminarytrading.viewmodel.CryptoViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CryptoViewModel by viewModels()
    private lateinit var cryptoAdapter: CryptoAdapter
    private val lineData = arrayListOf(1000, 1100, 1200, 1100)
    private val lineData2 = arrayListOf(2100, 2000, 1900, 2000)
    private val lineData3 = arrayListOf(900, 1100, 1200, 1000, 1150)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWindow()
        setupRecyclerView()
        observeViewModel()
        
        // Update prices every 30 seconds
        lifecycleScope.launch {
            while (true) {
                viewModel.updatePrices(listOf("bitcoin", "ethereum", "tron"))
                kotlinx.coroutines.delay(30000)
            }
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Уже на MainActivity
                    true
                }
                R.id.nav_study -> {
                    startActivity(Intent(this, StudyActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_news -> {
                    startActivity(Intent(this, GiftsActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                else -> false
            }
        }

        //StockList()
    }

    private fun setupWindow() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.WHITE
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }

    private fun setupRecyclerView() {
        cryptoAdapter = CryptoAdapter(ArrayList())
        binding.cryptoView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = cryptoAdapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.cryptoPrices.collectLatest { prices ->
                val cryptoList = prices.map { (id, price) ->
                    CryptoModel(
                        name = when(id) {
                            "bitcoin" -> "Bitcoin"
                            "ethereum" -> "Ethereum"
                            "tron" -> "Tron"
                            else -> id
                        },
                        symbol = when(id) {
                            "bitcoin" -> "BTC/USDT"
                            "ethereum" -> "ETH/USDT"
                            "tron" -> "TRX/USDT"
                            else -> "$id/USDT"
                        },
                        price = price,
                        changePercent = 0.0, // You can add 24h change from the API response
                        lineData = ArrayList(), // You can add historical data if needed
                        propertyAmount = viewModel.getCryptoBalance(id),
                        propertySize = viewModel.getCryptoBalance(id),
                        SellPrice1 = price * 1.001,
                        SellPrice2 = price * 1.002,
                        SellPrice3 = price * 1.003,
                        SellPrice4 = price * 1.004,
                        SellAmount1 = 0.1,
                        SellAmount2 = 0.2,
                        SellAmount3 = 0.15,
                        SellAmount4 = 0.05,
                        BuyPrice1 = price * 0.999,
                        BuyPrice2 = price * 0.998,
                        BuyPrice3 = price * 0.997,
                        BuyPrice4 = price * 0.996,
                        BuyPrice5 = price * 0.995,
                        BuyPrice6 = price * 0.994,
                        BuyPrice7 = price * 0.993,
                        BuyAmount1 = 0.3,
                        BuyAmount2 = 0.25,
                        BuyAmount3 = 0.2,
                        BuyAmount4 = 0.15,
                        BuyAmount5 = 0.1,
                        BuyAmount6 = 0.05,
                        BuyAmount7 = 0.03,
                        Open = price,
                        Close = price,
                        High = price * 1.01,
                        Low = price * 0.99,
                        DailyChange = 0.0,
                        DailyVol = 0.0,
                        SymbolLogo = id
                    )
                }
                cryptoAdapter.updateData(cryptoList)
            }
        }

        lifecycleScope.launch {
            viewModel.portfolioValue.collectLatest { value ->
                binding.portfolioValue.text = "$${String.format("%.2f", value)}"
            }
        }
    }

//    private fun StockList() {
//        binding.stockView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//
//        val domainArrayList = arrayListOf(
//            Model("NASDAQ100", "BTC", 110000.11, 2.1, lineData, 1.1, 0.1),
//            Model("S&P 500", "ETH", 2400.1, 5.2, lineData2, 1.1, 0.1),
//            Model("Dow Jones", "TRX", 661.16, 0.68, lineData3, 1.1, 0.1)
//        )
//
//        binding.stockView.adapter = StockAdapter(domainArrayList)
//    }
}