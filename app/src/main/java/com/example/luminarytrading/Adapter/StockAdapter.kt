package com.example.luminarytrading.Adapter

import android.graphics.Color
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.example.luminarytrading.Model.Model
import com.example.luminarytrading.databinding.ViewholderCryptoBinding
import com.example.luminarytrading.databinding.ViewholderStockBinding

class StockAdapter(private val dataList:ArrayList<Model>):
RecyclerView.Adapter<StockAdapter.ViewHolder>(){

    private val formatter = DecimalFormat("###,###,###,###.##")

    class ViewHolder(val binding: ViewholderStockBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockAdapter.ViewHolder {
        val binding = ViewholderStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)


    }

    override fun onBindViewHolder(holder: StockAdapter.ViewHolder, position: Int) {
        val item = dataList[position]

        holder.binding.apply {
            StockNameTxt.text = item.name
            StockPriceTxt.text = "$${formatter.format(item.price)}"
            changePercentTxt.text = "${item.changePercent}%"

            sparkLineLayout.setData(item.lineData)

            val changeColor = when {
                item.changePercent>=0-> Color.parseColor("#12c737")
                item.changePercent<0-> Color.parseColor("#ff0000")

                else-> Color.WHITE
            }

            changePercentTxt.setTextColor(changeColor)
            sparkLineLayout.sparkLineColor = changeColor

            val picName = when(item.name){
                "NASDAQ100"->"stock_1"
                "S&P 500"->"stock_2"
                "Dow Jones"->"stock1"
                else->""
            }

            val drawableResourceId = holder.itemView.context.resources.getIdentifier(
                picName, "drawable", holder.itemView.context.packageName
            )

            Glide.with(holder.itemView.context)
                .load(drawableResourceId)
                .into(logoimg)

        }

    }

    override fun getItemCount(): Int = dataList.size

}