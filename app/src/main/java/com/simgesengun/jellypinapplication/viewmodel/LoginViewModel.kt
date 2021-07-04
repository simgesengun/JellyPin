package com.simgesengun.jellypinapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simgesengun.jellypinapplication.entity.LoginResponse
import com.simgesengun.jellypinapplication.entity.User
import com.simgesengun.jellypinapplication.retrofit.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel : ViewModel() {

    val udaoi = ApiUtils.getUserDaoInterface()
    val loginResponse : MutableLiveData<User>

    init{
        loginResponse = MutableLiveData()
    }

    fun loginUser(mail_address: String, password: String){
        udaoi.searchUser(mail_address, password).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginResponse.value = null
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val usersList = response.body().users_list
                for (u in usersList) {
                    if (u.deger == 1) {
                        loginResponse.value = u
                        return
                    }
                }
                loginResponse.value = null
            }
        })
    }
}