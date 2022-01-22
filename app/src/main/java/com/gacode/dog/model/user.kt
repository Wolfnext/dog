package com.gacode.dog.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class user(
    @SerializedName("uid")
    val uid: String,
    @SerializedName("email")
    var email:String,
    @SerializedName("type")
    val type:String,
    @SerializedName("created_at")
    val created_at:String):Serializable

