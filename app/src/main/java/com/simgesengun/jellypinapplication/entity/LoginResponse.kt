package com.simgesengun.jellypinapplication.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(@SerializedName("kullanicilar")
                        @Expose
                        var users_list : List<User>) : Serializable {
}