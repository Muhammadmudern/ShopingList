package com.example.shopinglist.Domain

// Дата класс для отображения елементов

data class ShopItem(
    val name: String,
    val count: Int,
    val enable: Boolean,
    var id: Int = UNDEFINED_ID

){
    companion object{
        const val UNDEFINED_ID = -1
    }
}
