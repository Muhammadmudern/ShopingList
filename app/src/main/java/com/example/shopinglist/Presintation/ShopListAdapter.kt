package com.example.shopinglist.Presintation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglist.Domain.ShopItem
import com.example.shopinglist.R
import java.lang.RuntimeException

// в LinnerLayout можно добовлять View елементы
// В цикле для каждого элемента списка создать View мз нашего покета
// сам наш макет (xml) не являеться View елементом это просто файл
// Для приоброзавания нашего макета в View нам нужно использывовать LayoutInflator
// LayoutInflator - такой класс который умеет из макета создовать View

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {
    var count = 0

    var shiplist = listOf<ShopItem>()
        set(value) { // При установки значения вызвался метод notifyDataSetChanged для обновления данных
            field = value
            notifyDataSetChanged()
        }

    // Метод который показывает как создовать View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("ShopListAdapter", "onCreateViewHolder: count: ${++count}")
        val layout = when(viewType){
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            else -> throw RuntimeException("Unknow view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    // Метод показывает как вставить значения внутри этого View
    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = shiplist[position]
        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()
        viewHolder.view.setOnLongClickListener {
            true
        }
    }


    override fun getItemViewType(position: Int): Int {
        val item = shiplist[position]
        return if (item.enable) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED
    }

    override fun getItemCount(): Int {// Возрашяет количество элементов в колекции
        return shiplist.size
    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0
        const val MAX_POOL_SIZE = 5
    }
}