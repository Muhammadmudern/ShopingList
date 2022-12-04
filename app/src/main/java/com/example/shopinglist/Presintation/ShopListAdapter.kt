package com.example.shopinglist.Presintation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglist.Domain.ShopItem
import com.example.shopinglist.R
import java.lang.RuntimeException

// в LinnerLayout можно добовлять View елементы
// В цикле для каждого элемента списка создать View мз нашего покета
// сам наш макет (xml) не являеться View елементом это просто файл
// Для приоброзавания нашего макета в View нам нужно использывовать LayoutInflator
// LayoutInflator - такой класс который умеет из макета создовать View



// Теперь мы должны сделать так чтобы наши элементы при долгом нажатии менялись из активаного на неактвного
// для этого нам нужен interface методы которого мы будем вызвать внутри адаптера
// реализацию этого interface нужно сделать в активити где мы вызовим методы ViewModel



// ранее мы наследовались от RecyclerView когда использывали 1 способ и это было в полне нормально
// но так как теперь мы используем 2 способ DiffUtils поэтому наследуемся от ListAdapter
class ShopListAdapter : ListAdapter<ShopItem, ShopListAdapter.ShopItemViewHolder>(ShopItemDiffCallBack()) {
    var onShopItemLongClickListner: ((ShopItem) -> Unit)? = null
    var onShopItemClickListner: ((ShopItem) -> Unit)? = null

//    // Второй метод
//    // не требует работу со списком он это делает сам
//
//    var shiplist = listOf<ShopItem>()
//        set(value) { // При установки значения вызвался метод notifyDataSetChanged для обновления данных
//
//
//            // Это все первый метод создание DiffUtil
////            // Когда устанвливаем новое значение мы передем в нащ класс
////            // старый(shiplist) и новый(value) обьекты, нужен для сравнение 2 обьектов
////            val callback = ShopListDiffCallback(shiplist, value)
////            // Для того чтобы сделать вычесления чтобы узанть какие изменения произошли
////            // мы используем метод calculateDiff.
////            // После того как все вычесления сделаны diffResult сообщяет адаптеру какие методы нужно вызвать
////            val diffResult = DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
//
//
//            field = value
//        }

    // Метод который показывает как создовать View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
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
        val shopItem = getItem(position)
        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()
        viewHolder.view.setOnLongClickListener {
            onShopItemLongClickListner?.invoke(shopItem)
            true
        }
        viewHolder.view.setOnClickListener {
            onShopItemClickListner?.invoke(shopItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enable) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED
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