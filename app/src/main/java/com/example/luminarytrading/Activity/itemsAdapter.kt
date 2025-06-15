package com.example.luminarytrading.Activity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.luminarytrading.R
class ItemsAdapter(var items : List<item>, var context: Context) : RecyclerView.Adapter<ItemsAdapter.MyViewHolder>() {


    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.item_list_image)
        val title: TextView = view.findViewById(R.id.item_list_title)
        val desc: TextView = view.findViewById(R.id.item_list_desc)
        val level: TextView = view.findViewById(R.id.item_list_level)
        val btn: Button = view.findViewById(R.id.item_list_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = items[position].title
        holder.desc.text = items[position].desc
        if (items[position].level == "Новичок"){
            holder.level.text = "\uD83D\uDFE2" + items[position].level
        } else if(items[position].level == "Опытный"){
            holder.level.text = "\uD83D\uDFE1" + items[position].level
        } else{
            holder.level.text = "\uD83D\uDD34" + items[position].level
        }

        val image_id = context.resources.getIdentifier(
            items[position].image,
            "drawable",
            context.packageName
        )

        holder.image.setImageResource(image_id)
        holder.btn.setOnClickListener {
            val intent = Intent(context, itemActivity::class.java)
            intent.putExtra("itemTitle", items[position].title)
            intent.putExtra("itemText", items[position].text)
            context.startActivity(intent)
        }


    }
}