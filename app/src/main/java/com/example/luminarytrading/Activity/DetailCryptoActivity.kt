package com.example.luminarytrading.Activity

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.luminarytrading.Model.CryptoModel
import com.example.luminarytrading.R
import com.example.luminarytrading.databinding.ActivityDetailActivityBinding
import com.example.luminarytrading.databinding.ViewholderStockBinding

class DetailCryptoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailActivityBinding
    private lateinit var item:CryptoModel
    private var formatter: DecimalFormat = DecimalFormat("###,###,###.##")
    private var isBuyMode = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS

        )

        getBundle()
        setupListeners()
    }

    private fun setupListeners() {
        // Переключение между режимами покупки/продажи
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

        // Кнопки изменения количества
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

        // Обработка отправки ордера
        binding.sendOrderBtn.setOnClickListener {
            processOrder()
        }
    }

    private fun processOrder() {
        val priceText = binding.priceEdt.text.toString()
        val amountText = binding.amountEdt.text.toString()

        // Проверка введенных данных
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

        // Очистка полей
        binding.priceEdt.text.clear()
        binding.amountEdt.text.clear()

        // Показать уведомление об успехе
        val action = if (isBuyMode) "bought" else "sold"
        val message = "Successfully $action ${"%.4f".format(amount)} ${item.symbol} at ${"%.2f".format(price)}"
        showSuccessNotification(message)

        // Здесь можно добавить логику обработки ордера
        // processOrderLogic(price, amount)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showSuccessNotification(message: String) {
        // Создаем кастомное уведомление
        val inflater = layoutInflater
        val layout = inflater.inflate(
            R.layout.custom_notification,
            findViewById(R.id.custom_notification_container)
        )

        // Настраиваем текст
        val text = layout.findViewById<TextView>(R.id.notification_text)
        text.text = message

        // Создаем и настраиваем Toast
        val toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 100)
        toast.show()
    }
    private fun getBundle() {
        item = intent.getSerializableExtra("object") as CryptoModel

        binding.symbolNameTxt.text = item.symbol
        binding.priceTxt.text = item.price.toString()
        binding.changePercentTxt.text = item.changePercent.toString()+"$"
        binding.pSellTxt1.text = (formatter.format(item.SellPrice1)?:0).toString()
        binding.pSellTxt2.text = (formatter.format(item.SellPrice2)?:0).toString()
        binding.pSellTxt3.text = (formatter.format(item.SellPrice3)?:0).toString()
        binding.pSellTxt4.text = (formatter.format(item.SellPrice4)?:0).toString()

        binding.aSellTxt1.text = (formatter.format(item.SellAmount1)?:0).toString()
        binding.aSellTxt2.text = (formatter.format(item.SellAmount2)?:0).toString()
        binding.aSellTxt3.text = (formatter.format(item.SellAmount3)?:0).toString()
        binding.aSellTxt4.text = (formatter.format(item.SellAmount4)?:0).toString()

        binding.pBuyTxt1.text = (formatter.format(item.BuyPrice1)?:0).toString()
        binding.pBuyTxt2.text = (formatter.format(item.BuyPrice2)?:0).toString()
        binding.pBuyTxt3.text = (formatter.format(item.BuyPrice3)?:0).toString()
        binding.pBuyTxt4.text = (formatter.format(item.BuyPrice4)?:0).toString()
        binding.pBuyTxt5.text = (formatter.format(item.BuyPrice5)?:0).toString()
        binding.pBuyTxt6.text = (formatter.format(item.BuyPrice6)?:0).toString()
        binding.pBuyTxt7.text = (formatter.format(item.BuyPrice7)?:0).toString()

        binding.aBuyTxt1.text = item.BuyAmount1.toString()
        binding.aBuyTxt2.text = item.BuyAmount2.toString()
        binding.aBuyTxt3.text = item.BuyAmount3.toString()
        binding.aBuyTxt4.text = item.BuyAmount4.toString()
        binding.aBuyTxt5.text = item.BuyAmount5.toString()
        binding.aBuyTxt6.text = item.BuyAmount6.toString()
        binding.aBuyTxt7.text = item.BuyAmount7.toString()

        binding.openTxt.text = (formatter.format(item.Open)?:0).toString()
        binding.closeTxt.text = (formatter.format(item.Close)?:0).toString()
        binding.highTxt.text = (formatter.format(item.High)?:0).toString()
        binding.lowTxt.text = (formatter.format(item.Low)?:0).toString()
        binding.dailyChangeTxt.text = item.DailyChange.toString()+"%"
        binding.dailyVolumeTxt.text = "$"+item.DailyVol.toString()+"T"

        if(item.changePercent>0){
            binding.priceTxt.setTextColor(resources.getColor(R.color.green))
            binding.changePercentTxt.setTextColor(resources.getColor(R.color.green))
        }else{
            binding.priceTxt.setTextColor(resources.getColor(R.color.red))
            binding.changePercentTxt.setTextColor(resources.getColor(R.color.red))
        }


        val drawable = resources.getIdentifier(item.SymbolLogo, "drawable", packageName)

        Glide.with(this)
            .load(drawable)
            .into(binding.logoImg)

        binding.backBtn.setOnClickListener {
            finish()
        }

    }
}