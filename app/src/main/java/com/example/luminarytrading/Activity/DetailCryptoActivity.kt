package com.example.luminarytrading.Activity

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.luminarytrading.Model.CryptoModel
import com.example.luminarytrading.R
import com.example.luminarytrading.database.TradeType
import com.example.luminarytrading.databinding.ActivityDetailActivityBinding
import com.example.luminarytrading.viewmodel.CryptoViewModel

class DetailCryptoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailActivityBinding
    private lateinit var item: CryptoModel
    private val viewModel: CryptoViewModel by viewModels()
    private var formatter: DecimalFormat = DecimalFormat("###,###,###.##")
    private var isBuyMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWindow()
        getBundle()
        setupListeners()
    }

    private fun setupWindow() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun setupListeners() {
        binding.BuyPositionBtn.setOnClickListener {
            isBuyMode = true
            binding.apply {
                BuyPositionBtn.setBackgroundResource(R.drawable.green_bg)
                sellPositionBtn.setBackgroundResource(R.drawable.semi_black_bg)
                sendOrderBtn.setBackgroundResource(R.drawable.green_bg)
                sendOrderBtn.text = "Buy " + item.symbol.replace("/USDT", "")
            }
        }

        binding.sellPositionBtn.setOnClickListener {
            isBuyMode = false
            binding.apply {
                BuyPositionBtn.setBackgroundResource(R.drawable.semi_black_bg)
                sellPositionBtn.setBackgroundResource(R.drawable.red_bg)
                sendOrderBtn.setBackgroundResource(R.drawable.red_bg)
                sendOrderBtn.text = "Sell " + item.symbol.replace("/USDT", "")
            }
        }

        binding.plusAmountBtn.setOnClickListener {
            val currentAmount = binding.amountEdt.text.toString().toDoubleOrNull() ?: 0.0
            binding.amountEdt.setText("%.4f".format(currentAmount + 0.001))
        }

        binding.minusAmountBtn.setOnClickListener {
            val currentAmount = binding.amountEdt.text.toString().toDoubleOrNull() ?: 0.0
            if (currentAmount > 0) {
                binding.amountEdt.setText("%.4f".format(currentAmount - 0.001))
            }
        }

        binding.sendOrderBtn.setOnClickListener {
            processOrder()
        }

        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    private fun processOrder() {
        val priceText = binding.priceEdt.text.toString()
        val amountText = binding.amountEdt.text.toString()

        if (priceText.isEmpty() || amountText.isEmpty()) {
            showToast("Please enter price and amount")
            return
        }

        val price = priceText.toDoubleOrNull()
        val amount = amountText.toDoubleOrNull()

        if (price == null || amount == null || price <= 0 || amount <= 0) {
            showToast("Invalid price or amount")
            return
        }

        // Add trade to database
        viewModel.addTrade(
            cryptoId = item.SymbolLogo,
            amount = amount,
            price = price,
            type = if (isBuyMode) TradeType.BUY else TradeType.SELL
        )

        // Clear input fields
        binding.priceEdt.text.clear()
        binding.amountEdt.text.clear()

        // Show success notification
        val action = if (isBuyMode) "bought" else "sold"
        val message = "Successfully $action ${"%.4f".format(amount)} ${item.symbol} at ${"%.2f".format(price)}"
        showSuccessNotification(message)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showSuccessNotification(message: String) {
        val inflater = layoutInflater
        val layout = inflater.inflate(
            R.layout.custom_notification,
            findViewById(R.id.custom_notification_container)
        )

        val text = layout.findViewById<TextView>(R.id.notification_text)
        text.text = message

        val toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 100)
        toast.show()
    }

    private fun getBundle() {
        item = intent.getSerializableExtra("object") as CryptoModel

        binding.apply {
            symbolNameTxt.text = item.symbol
            priceTxt.text = item.price.toString()
            changePercentTxt.text = item.changePercent.toString() + "%"
            
            // Set order book prices
            pSellTxt1.text = formatter.format(item.SellPrice1)
            pSellTxt2.text = formatter.format(item.SellPrice2)
            pSellTxt3.text = formatter.format(item.SellPrice3)
            pSellTxt4.text = formatter.format(item.SellPrice4)

            aSellTxt1.text = formatter.format(item.SellAmount1)
            aSellTxt2.text = formatter.format(item.SellAmount2)
            aSellTxt3.text = formatter.format(item.SellAmount3)
            aSellTxt4.text = formatter.format(item.SellAmount4)

            pBuyTxt1.text = formatter.format(item.BuyPrice1)
            pBuyTxt2.text = formatter.format(item.BuyPrice2)
            pBuyTxt3.text = formatter.format(item.BuyPrice3)
            pBuyTxt4.text = formatter.format(item.BuyPrice4)
            pBuyTxt5.text = formatter.format(item.BuyPrice5)
            pBuyTxt6.text = formatter.format(item.BuyPrice6)
            pBuyTxt7.text = formatter.format(item.BuyPrice7)

            aBuyTxt1.text = formatter.format(item.BuyAmount1)
            aBuyTxt2.text = formatter.format(item.BuyAmount2)
            aBuyTxt3.text = formatter.format(item.BuyAmount3)
            aBuyTxt4.text = formatter.format(item.BuyAmount4)
            aBuyTxt5.text = formatter.format(item.BuyAmount5)
            aBuyTxt6.text = formatter.format(item.BuyAmount6)
            aBuyTxt7.text = formatter.format(item.BuyAmount7)

            openTxt.text = formatter.format(item.Open)
            closeTxt.text = formatter.format(item.Close)
            highTxt.text = formatter.format(item.High)
            lowTxt.text = formatter.format(item.Low)
            dailyChangeTxt.text = item.DailyChange.toString() + "%"
            dailyVolumeTxt.text = "$" + item.DailyVol.toString() + "T"

            if (item.changePercent > 0) {
                priceTxt.setTextColor(resources.getColor(R.color.green))
                changePercentTxt.setTextColor(resources.getColor(R.color.green))
            } else {
                priceTxt.setTextColor(resources.getColor(R.color.red))
                changePercentTxt.setTextColor(resources.getColor(R.color.red))
            }

            val drawable = resources.getIdentifier(item.SymbolLogo, "drawable", packageName)
            Glide.with(this@DetailCryptoActivity)
                .load(drawable)
                .into(logoImg)
        }
    }
}