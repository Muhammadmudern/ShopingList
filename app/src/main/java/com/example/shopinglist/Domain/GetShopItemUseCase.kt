package com.example.shopinglist.Domain

// Метод получения информации о покупке в списке по его ID
// Добовляем наш репозиторий в метод чтобы реализовывать данные методы

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItem(shopItemId: Int): ShopItem{
        return shopListRepository.getShopItem(shopItemId)
    }
}