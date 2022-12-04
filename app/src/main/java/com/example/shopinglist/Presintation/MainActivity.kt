package com.example.shopinglist.Presintation

import android.media.MediaRouter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglist.Domain.ShopItem
import com.example.shopinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecycleView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shoplist.observe(this){
            shopListAdapter.submitList(it)
        }
    }

    // Метод который будет настраивать RecycleView
    private fun setupRecycleView(){
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        with(rvShopList) {
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }
        // Настройка при долгом нажатии на элемент делает его активным или нет
        setShopItemLongClick()

        // Настройка при одном клике который возвращяет данные элемента
        setShopItemOnClick()

        // Удаление свайпом
        DeleteOnSwipeElement(rvShopList)

    }

    private fun DeleteOnSwipeElement(rvShopList: RecyclerView?) {
        val simpleCallBack = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        }
        val itemTuchHelper = ItemTouchHelper(simpleCallBack)
        itemTuchHelper.attachToRecyclerView(rvShopList)
    }

    private fun setShopItemOnClick() {
        shopListAdapter.onShopItemClickListner = {
            Log.d("MainActivity", it.toString())
        }
    }

    private fun setShopItemLongClick() {
        shopListAdapter.onShopItemLongClickListner = {
            viewModel.changeEnableState(it)
        }
    }
}