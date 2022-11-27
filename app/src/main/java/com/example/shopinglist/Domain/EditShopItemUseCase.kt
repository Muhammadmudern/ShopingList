package com.example.shopinglist.Domain

// Метод который изменяет уже сохраненные данные
// Добовляем наш репозиторий в метод чтобы реализовывать данные методы

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}