package com.simgesengun.jellypinapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simgesengun.jellypinapplication.entity.Item
import com.simgesengun.jellypinapplication.repo.ItemDaoRepository
import com.simgesengun.jellypinapplication.retrofit.ApiUtils

class AllItemsViewModel : ViewModel() {
    var itemsList = MutableLiveData<List<Item>>()
    val idaor = ItemDaoRepository()

    init {
        loadItems()
        itemsList = idaor.getItemsList()
    }

    fun loadItems(){
        idaor.getAllItems("simgesengun")
    }

    fun updateSaleItem(id: Int, is_on_sale : Int){
        idaor.updateSaleItem(id,is_on_sale)
        loadItems()
    }
}