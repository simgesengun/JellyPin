package com.simgesengun.jellypinapplication.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item (@SerializedName("id")
                 @Expose
                 var id : Int,
                 @SerializedName("urun_adi")
                 @Expose
                 var name : String,
                 @SerializedName("urun_fiyat")
                 @Expose
                 var price : String,
                 @SerializedName("urun_aciklama")
                 @Expose
                 var description : String,
                 @SerializedName("urun_gorsel_url")
                 @Expose
                 var picture_url : String,
                 @SerializedName("sepet_durum")
                 @Expose
                 var is_on_cart : Int,
                 @SerializedName("urun_indirimli_mi")
                 @Expose
                 var is_on_sale : Int) : Serializable {
}