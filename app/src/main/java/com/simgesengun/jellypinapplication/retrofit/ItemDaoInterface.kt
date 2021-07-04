package com.simgesengun.jellypinapplication.retrofit

import com.simgesengun.contactsapplication.entity.ItemResponse
import com.simgesengun.jellypinapplication.entity.CRUDResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ItemDaoInterface {
    @POST("urunler.php")
    @FormUrlEncoded
    fun getAllItems(@Field("satici_adi") seller_name: String): Call<ItemResponse>

    @POST("urun_ekle.php")
    @FormUrlEncoded
    fun insertItem(
        @Field("satici_adi") seller_name: String,
        @Field("urun_adi") name: String,
        @Field("urun_fiyat") price: String,
        @Field("urun_aciklama") description: String,
        @Field("urun_gorsel_url") picture_url: String
    ): Call<CRUDResponse>

    @POST("indirimli_urun_durum_degistir.php")
    @FormUrlEncoded
    fun updateOnSaleItem(
        @Field("id") id: Int,
        @Field("urun_indirimli_mi") is_on_sale: Int
    ): Call<CRUDResponse>

    @POST("sepet_durum_degistir.php")
    @FormUrlEncoded
    fun updateCartItem(
        @Field("id") id: Int,
        @Field("sepet_durum") is_on_cart: Int
    ): Call<CRUDResponse>
}