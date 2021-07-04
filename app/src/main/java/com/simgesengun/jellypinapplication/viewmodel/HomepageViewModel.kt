package com.simgesengun.jellypinapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simgesengun.jellypinapplication.entity.Item
import com.simgesengun.jellypinapplication.repo.ItemDaoRepository
import com.simgesengun.jellypinapplication.retrofit.ApiUtils

class HomepageViewModel : ViewModel() {
    var itemsList = MutableLiveData<List<Item>>()
    var cartItemsList = MutableLiveData<List<Item>>()
    val idaor = ItemDaoRepository()

    init {
        loadItems()
        loadCartItems()
        itemsList = idaor.getItemsList()
        cartItemsList = idaor.getCartItemsList()
    }

    fun loadItems(){
        idaor.getAllItems("simgesengun")
    }
    fun loadCartItems(){
        idaor.getAllCartItems("simgesengun")
    }

    fun updateCartItem(id: Int, is_on_cart : Int){
        idaor.updateCartItem(id,is_on_cart)
        loadCartItems()
    }
}