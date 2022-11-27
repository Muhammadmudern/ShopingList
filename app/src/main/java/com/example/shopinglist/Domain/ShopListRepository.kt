package com.example.shopinglist.Domain

import androidx.lifecycle.LiveData

// Так как наш Domain не связан ни с БД ни с GUI и отделен чтобы он так же оставался single
// нам нужно создать Interface класс куда будут поступать все данные и от него дальше будут уходить данные
// так как наш Domain будет видеть его как черный ящик в котором есть методы но нет реализации их

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem)
    fun deleteShopItem(shopItem: ShopItem)
    fun editShopItem(shopItem: ShopItem)
    fun getShopItem(shopItemId: Int): ShopItem
    fun getShopList(): LiveData<List<ShopItem>>
}