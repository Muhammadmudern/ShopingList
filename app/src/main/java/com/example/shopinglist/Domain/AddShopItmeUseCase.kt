package com.example.shopinglist.Domain

// Метод добавляет элемент в список
// Добовляем наш репозиторий в метод чтобы реализовывать данные методы

class AddShopItmeUseCase(private val shopListRepository: ShopListRepository) {
    fun addShopItem(shopItem: ShopItem){
        shopListRepository.addShopItem(shopItem)
    }
}