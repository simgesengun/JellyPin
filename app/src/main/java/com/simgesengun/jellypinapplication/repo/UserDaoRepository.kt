package com.simgesengun.jellypinapplication.repo

import androidx.lifecycle.MutableLiveData
import com.simgesengun.jellypinapplication.entity.CRUDResponse
import com.simgesengun.jellypinapplication.entity.User
import com.simgesengun.jellypinapplication.retrofit.ApiUtils
import com.simgesengun.jellypinapplication.retrofit.UserDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDaoRepository {
    private val udaoi : UserDaoInterface
    private val user : MutableLiveData<User>

    init{
        udaoi = ApiUtils.getUserDaoInterface()
        user = MutableLiveData()
    }

    fun newUser(mail_address : String, password : String, name_surname : String, phone_number : String){
        udaoi.insertUser(mail_address, password, name_surname, phone_number)
            .enqueue(object : Callback<CRUDResponse> {
                override fun onResponse(
                    call: Call<CRUDResponse>?,
                    response: Response<CRUDResponse>?
                ) {
                }

                override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?){
                }
        })
    }

}