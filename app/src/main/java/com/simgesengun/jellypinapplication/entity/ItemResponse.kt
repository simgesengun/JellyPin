package com.simgesengun.contactsapplication.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.simgesengun.jellypinapplication.entity.Item

data class ItemResponse(@SerializedName("urunler")
                          @Expose
                          var items_list : List<Item>,
                        @SerializedName("success")
                          @Expose
                          var success : Int) {
}