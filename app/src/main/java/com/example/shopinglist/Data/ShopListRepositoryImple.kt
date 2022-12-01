package com.example.shopinglist.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopinglist.Domain.ShopItem
import com.example.shopinglist.Domain.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

object ShopListRepositoryImple: ShopListRepository {
    // Мы пока не будем подключать БД можем сделать потом сами
    // Пока все значения будут храниться в переменных и каждый раз когда мы будем перезапускать приложение
    // то все наши данные исчезнут

    private val shopList = sortedSetOf<ShopItem>({ p0, p1 -> p0.id.compareTo(p1.id) })
    private var autoIncrementId = 0

    // MutableLiveData - Обычный LiveData но в который мы сами можем ставить обьекты
    private val shopListLiveData = MutableLiveData<List<ShopItem>>()// Обьект LiveData который мы будем возврашять

    init {
        for (i in 1 until 1000){
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        upDateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        upDateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldeEment = getShopItem(shopItem.id)
        shopList.remove(oldeEment)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find{ it.id == shopItemId
        } ?: throw RuntimeException("Элементь по id $shopItemId не найден!")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLiveData
    }

    //При удалении, добавлении и изменений нам нужен метод который будет обновлять дату
    private fun upDateList(){
        shopListLiveData.value = shopList.toList()
    }
}