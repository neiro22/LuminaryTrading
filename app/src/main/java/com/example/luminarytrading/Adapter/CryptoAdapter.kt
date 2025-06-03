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

class CryptoAdapter(private val dataList:ArrayList<Model>):
RecyclerView.Adapter<CryptoAdapter.ViewHolder>(){

    private val formatter = DecimalFormat("###,###,###,###.##")

    class ViewHolder(val binding: ViewholderCryptoBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoAdapter.ViewHolder {
        val binding = ViewholderCryptoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)


    }

    override fun onBindViewHolder(holder: CryptoAdapter.ViewHolder, position: Int) {
        val item = dataList[position]

        holder.binding.apply {
            cryptoNameTxt.text = item.name
            cryptoPriceTxt.text = "$${formatter.format(item.price)}"
            changePercentTxt.text = "${item.changePercent}%"
            propertySizeTxt.text = "${item.propertySize}${item.propertySize}"
            propertyAmountTxt.text = "$${formatter.format(item.propertyAmount)}"

            sparkLineLayout.setData(item.lineData)

            val changeColor = when {
                item.changePercent>=0-> Color.parseColor("#12c737")
                item.changePercent<0-> Color.parseColor("#ff0000")

                else-> Color.WHITE
            }

            changePercentTxt.setTextColor(changeColor)
            sparkLineLayout.sparkLineColor = changeColor

            val drawableResourceId = holder.itemView.context.resources.getIdentifier(
                item.name, "drawable", holder.itemView.context.packageName
            )

            Glide.with(holder.itemView.context)
                .load(drawableResourceId)
                .into(logoImg)

        }

    }

    override fun getItemCount(): Int = dataList.size

}