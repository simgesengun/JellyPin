package com.simgesengun.jellypinapplication.retrofit

import com.simgesengun.jellypinapplication.retrofit.RetrofitClient.Companion.getClient

class ApiUtils {
    companion object{
        val BASE_URL : String = "http://upschool.canerture.com/"

        fun getItemDaoInterface() : ItemDaoInterface {
            return getClient(BASE_URL).create(ItemDaoInterface::class.java)
        }
        fun getUserDaoInterface() : UserDaoInterface {
            return getClient(BASE_URL).create(UserDaoInterface::class.java)
        }
    }
}