package com.simgesengun.jellypinapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simgesengun.jellypinapplication.entity.Item
import com.simgesengun.jellypinapplication.repo.ItemDaoRepository

class CampaignDetailsViewModel : ViewModel() {
    var saleItemsList = MutableLiveData<List<Item>>()
    var cartItemsList = MutableLiveData<List<Item>>()
    val idaor = ItemDaoRepository()

    init {
        loadSaleItems()
        loadCartItems()
        saleItemsList = idaor.getSaleItemsList()
        cartItemsList = idaor.getCartItemsList()
    }

    fun loadSaleItems(){
        idaor.getAllSaleItems("simgesengun")
    }
    fun loadCartItems(){
        idaor.getAllCartItems("simgesengun")
    }
    fun updateCartItem(id: Int, is_on_cart : Int){
        idaor.updateCartItem(id,is_on_cart)
        loadCartItems()
    }
}