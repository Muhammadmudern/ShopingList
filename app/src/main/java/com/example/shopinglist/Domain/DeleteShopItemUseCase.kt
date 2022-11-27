package com.example.shopinglist.Domain

// Метод для удаления данных
// Добовляем наш репозиторий в метод чтобы реализовывать данные методы

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteShopItem(shopItem: ShopItem){
        shopListRepository.deleteShopItem(shopItem)
    }
}