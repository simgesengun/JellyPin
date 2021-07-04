package com.simgesengun.jellypinapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simgesengun.jellypinapplication.entity.Item
import com.simgesengun.jellypinapplication.repo.ItemDaoRepository

class CartViewModel : ViewModel() {
    var cartItemsList = MutableLiveData<List<Item>>()
    var totalPrice = MutableLiveData<Double>()
    val idaor = ItemDaoRepository()

    init {
        loadCartItems()
        cartItemsList = idaor.getCartItemsList()
        totalPrice = idaor.getTotalPrice()
    }

    fun loadCartItems(){
        idaor.getAllCartItems("simgesengun")
        cartItemsList = idaor.getCartItemsList()
    }

    fun removeAllCartItems() : Int{
        val temp: Int = totalPrice.value!!.toInt() / 10
        return if(cartItemsList.value != null) {
            for (i in cartItemsList.value!!.indices) {
                idaor.updateCartItem(cartItemsList.value!![i].id, 0)
                loadCartItems()
            }
            loadCartItems()
            temp
        } else 0
    }

    fun updateCartItem(id: Int, is_on_cart : Int){
        idaor.updateCartItem(id,is_on_cart)
        loadCartItems()
    }

}