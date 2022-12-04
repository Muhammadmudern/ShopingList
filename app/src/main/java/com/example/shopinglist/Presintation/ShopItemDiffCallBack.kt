package com.example.shopinglist.Presintation

import androidx.recyclerview.widget.DiffUtil
import com.example.shopinglist.Domain.ShopItem

// Второй способ
// так как мы используем RecycleView который каждый раз создает 11 новые видемые элементы
// при каждом изменений или удалений элемента, но в этом классе мы будем использывовать методы
// которые помгут нам быстрее реализовыать и создовать не каждый раз по 11 ViewHolders
// только 1 добавленный элемент тем самым ускорит работу программы + анимации при удалиении и т.д.


class ShopItemDiffCallBack: DiffUtil.ItemCallback<ShopItem>(){

    // В отличее от первого способо теперь эти методы принемают не позицию старого и нового элемента
    // этот метод принемает сами элементы, так же проверяет их через id
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    // этот метод так же принемает сами элементы старые и новые
    // проверяет изменения элемента
    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}