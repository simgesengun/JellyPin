package com.simgesengun.jellypinapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.simgesengun.jellypinapplication.repo.ItemDaoRepository

class AddItemViewModel : ViewModel() {
    val idaor = ItemDaoRepository()

    fun addItem(name : String, price : String, description : String, pictureUrl : String){
        idaor.newItem("simgesengun",name, price, description, pictureUrl)
    }
}