package com.example.luminarytrading.Adapter

import android.content.Intent
import android.graphics.Color
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.luminarytrading.Activity.DetailCryptoActivity
import com.example.luminarytrading.Model.CryptoModel
import com.example.luminarytrading.databinding.ViewholderCryptoBinding

class CryptoAdapter(private var dataList: ArrayList<CryptoModel>) :
    RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {

    private val formatter = DecimalFormat("###,###,###,###.##")

    class ViewHolder(val binding: ViewholderCryptoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderCryptoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        holder.binding.apply {
            cryptoNameTxt.text = item.name
            cryptoPriceTxt.text = "$${formatter.format(item.price)}"
            changePercentTxt.text = "${item.changePercent}%"
            propertySizeTxt.text = "${item.propertySize}${item.symbol}"
            propertyAmountTxt.text = "$${formatter.format(item.propertyAmount)}"

            sparkLineLayout.setData(item.lineData)

            val changeColor = when {
                item.changePercent >= 0 -> Color.parseColor("#12c737")
                item.changePercent < 0 -> Color.parseColor("#ff0000")
                else -> Color.WHITE
            }

            changePercentTxt.setTextColor(changeColor)
            sparkLineLayout.sparkLineColor = changeColor

            val drawableResourceId = holder.itemView.context.resources.getIdentifier(
                item.SymbolLogo, "drawable", holder.itemView.context.packageName
            )

            Glide.with(holder.itemView.context)
                .load(drawableResourceId)
                .into(logoImg)

            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, DetailCryptoActivity::class.java)
                intent.putExtra("object", item)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun updateData(newData: List<CryptoModel>) {
        dataList.clear()
        dataList.addAll(newData)
        notifyDataSetChanged()
    }
}