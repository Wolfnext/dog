package com.gacode.dog.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class token(
    @SerializedName("scope")
    val scope: String,
    @SerializedName("expire_date")
                 var expire_date:Long,
    @SerializedName("refresh_token")
                 val refresh_token:String,
    @SerializedName("access_token")
                 val access_token:String):Serializable

