package com.simgesengun.jellypinapplication.retrofit

import com.simgesengun.contactsapplication.entity.ItemResponse
import com.simgesengun.jellypinapplication.entity.CRUDResponse
import com.simgesengun.jellypinapplication.entity.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UserDaoInterface {
    @POST("uye_ol.php")
    @FormUrlEncoded
    fun insertUser(@Field("mail_adres") mail_address : String,
                   @Field("sifre") password : String,
                   @Field("ad_soyad") name_surname : String,
                   @Field("telefon") phone_number : String) : Call<CRUDResponse>


    @POST("giris_yap.php")
    @FormUrlEncoded
    fun searchUser(@Field("mail_adres") mail_address: String,
                  @Field("sifre") password: String) : Call<LoginResponse>



}