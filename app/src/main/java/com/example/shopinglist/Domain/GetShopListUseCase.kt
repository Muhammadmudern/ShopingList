package com.example.shopinglist.Domain

import androidx.lifecycle.LiveData

// Метод который получает весь список покупок
// Добовляем наш репозиторий в метод чтобы реализовывать данные методы

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopList(): LiveData<List<ShopItem>>{
        return shopListRepository.getShopList()
    }
}