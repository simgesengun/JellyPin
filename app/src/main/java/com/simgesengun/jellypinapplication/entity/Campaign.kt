package com.simgesengun.jellypinapplication.entity

import java.io.Serializable

data class Campaign(val name : Int, val picture_url : String, val description : Int, val min_user_level : Int) : Serializable{
}