package com.example.shopinglist.Presintation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopinglist.Data.ShopListRepositoryImple
import com.example.shopinglist.Domain.*

class MainViewModel: ViewModel() {
    private val repository = ShopListRepositoryImple

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    // Отображаем список элементов

    val shoplist = getShopListUseCase.getShopList() // MutableLiveData - Обычный LiveData но в который мы сами можем ставить обьекты


    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItme =shopItem.copy(enable = !shopItem.enable)
        editShopItemUseCase.editShopItem(newItme)
    }
}