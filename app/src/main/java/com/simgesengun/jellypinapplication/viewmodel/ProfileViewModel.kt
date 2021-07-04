package com.simgesengun.jellypinapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simgesengun.jellypinapplication.entity.Item
import com.simgesengun.jellypinapplication.repo.ItemDaoRepository

class ProfileViewModel : ViewModel() {
    var cartItemsList = MutableLiveData<List<Item>>()
    val idaor = ItemDaoRepository()

    init {
        loadCartItems()
        cartItemsList = idaor.getCartItemsList()
    }

    fun loadCartItems(){
        idaor.getAllCartItems("simgesengun")
    }
}