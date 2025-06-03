package com.example.luminarytrading.Activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.luminarytrading.Adapter.CryptoAdapter
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


    }

    private fun CryptoList() {
        binding.cryptoView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val domainArrayList = arrayListOf(
            Model("Bitcoin", "BTC", 110000.11, 2.1, lineData, 1.1, 0.1),
            Model("Ethereum", "ETH", 2400.1, 5.2, lineData2, 1.1, 0.1),
            Model("Tron", "TRX", 661.16, 0.68, lineData3, 1.1, 0.1)
        )
        binding.cryptoView.adapter = CryptoAdapter(domainArrayList)

    }
}