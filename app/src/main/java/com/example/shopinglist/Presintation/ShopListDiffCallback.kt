package com.example.shopinglist.Presintation

import androidx.recyclerview.widget.DiffUtil
import com.example.shopinglist.Domain.ShopItem

// Превый способ
// так как мы используем RecycleView который каждый раз создает 11 новые видемые элементы
// при каждом изменений или удалений элемента, но в этом классе мы будем использывовать методы
// которые помгут нам быстрее реализовыать и создовать не каждый раз по 11 ViewHolders
// только 1 добавленный элемент тем самым ускорит работу программы + анимации при удалиении и т.д.


// ----------------------------------------------------------------------------------------------
// Но есть и 2 метод который на много быстрее мы этот класс и метод используем лишь для того
// чтобы узнать что такой метод просто существует


class ShopListDiffCallback(private val oldList: List<ShopItem>,
                           private val newList: List<ShopItem>): DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    // Принемает позицию старого эелемента и позицию нового элемента
    // Проверяет элемент который изменен через ID
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    // Проверяет не сами обьекты а их содержимое, поменялись ли поля самого обьекта
    // Выведит true если все поля элемента не изменелись
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}