package com.simgesengun.jellypinapplication.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User (@SerializedName("deger")
                      @Expose
                      var deger : Int,
                 @SerializedName("mail_adres")
                      @Expose
                      var mail_address : String,
                 @SerializedName("sifre")
                      @Expose
                      var password : String,
                 @SerializedName("ad_soyad")
                      @Expose
                      var name_surname : String,
                 @SerializedName("telefon")
                      @Expose
                      var phone_number : String) : Serializable {
}