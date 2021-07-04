package com.simgesengun.jellypinapplication.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.simgesengun.contactsapplication.entity.ItemResponse
import com.simgesengun.jellypinapplication.entity.CRUDResponse
import com.simgesengun.jellypinapplication.entity.Item
import com.simgesengun.jellypinapplication.retrofit.ApiUtils
import com.simgesengun.jellypinapplication.retrofit.ItemDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDaoRepository {
    private val idaoi : ItemDaoInterface
    private val itemsList : MutableLiveData<List<Item>>
    private val cartItemsList : MutableLiveData<List<Item>>
    private val saleItemsList : MutableLiveData<List<Item>>
    private val totalPrice : MutableLiveData<Double>

    init{
        idaoi = ApiUtils.getItemDaoInterface()
        itemsList = MutableLiveData()
        cartItemsList = MutableLiveData()
        saleItemsList = MutableLiveData()
        totalPrice = MutableLiveData()
        totalPrice.value = 0.0

    }

    fun getItemsList() : MutableLiveData<List<Item>> {
        return itemsList
    }

    fun getCartItemsList() : MutableLiveData<List<Item>> {
        return cartItemsList
    }

    fun getSaleItemsList() : MutableLiveData<List<Item>> {
        return saleItemsList
    }

    fun getTotalPrice() : MutableLiveData<Double> {
        return totalPrice
    }

    fun getAllSaleItems(saler_name : String) {
        idaoi.getAllItems(saler_name).enqueue(object : Callback<ItemResponse> {
            override fun onResponse(call: Call<ItemResponse>?, response: Response<ItemResponse>) {
                val temp = response.body().items_list
                val list = mutableListOf<Item>()
                for(item in temp){
                    if(item.is_on_sale == 1){
                        list.add(item)
                    }
                }
                saleItemsList.value = list
            }

            override fun onFailure(call: Call<ItemResponse>?, t: Throwable?) {
                if(t!=null){
                    Log.e("getallcartitems",t.toString())
                }
            }
        })
    }

    fun getAllCartItems(saler_name : String) {
        idaoi.getAllItems(saler_name).enqueue(object : Callback<ItemResponse> {
            override fun onResponse(call: Call<ItemResponse>?, response: Response<ItemResponse>) {
                val temp = response.body().items_list
                val list = mutableListOf<Item>()
                var price = 0.0
                for(item in temp){
                    if(item.is_on_cart == 1){
                        list.add(item)
                        price += item.price.toDouble()
                    }
                }
                totalPrice.value = price
                cartItemsList.value = list
            }

            override fun onFailure(call: Call<ItemResponse>?, t: Throwable?) {
                if(t!=null){
                    Log.e("getallcartitems",t.toString())
                }
            }
        })
    }

    fun getAllItems(saler_name : String) {
        idaoi.getAllItems(saler_name).enqueue(object : Callback<ItemResponse> {
            override fun onResponse(call: Call<ItemResponse>?, response: Response<ItemResponse>) {
                val list = response.body().items_list
                itemsList.value = list
            }

            override fun onFailure(call: Call<ItemResponse>?, t: Throwable?) {
                if(t!=null){
                    Log.e("getallitems",t.toString())
                }
            }
        })
    }

    fun newItem(seller_name : String, name : String, price : String, description : String, picture_url : String){
        idaoi.insertItem(seller_name, name, price, description, picture_url).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>) {}
            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {}
        })
    }
    fun updateSaleItem(id : Int, is_on_sale : Int){
        idaoi.updateOnSaleItem(id, is_on_sale).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>) {}
            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {}
        })
    }
    fun updateCartItem(id : Int, is_on_cart : Int){
        idaoi.updateCartItem(id, is_on_cart).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>) {}
            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {}
        })
    }
}