package com.simgesengun.jellypinapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simgesengun.jellypinapplication.entity.Item
import com.simgesengun.jellypinapplication.repo.ItemDaoRepository

class ItemDetailsViewModel : ViewModel() {
    val idaor = ItemDaoRepository()

    fun updateCartItem(id: Int, is_on_cart : Int){
        idaor.updateCartItem(id,is_on_cart)

    }
}