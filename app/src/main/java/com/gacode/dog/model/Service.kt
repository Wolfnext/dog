package com.gacode.dog.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Service (
    @SerializedName("type")
    var type:String,
    @SerializedName("maxSize")
    var maxSize:Int,
    @SerializedName("daysOfWeek")
    var daysOfWeek:Int,
    @SerializedName("time")
    var time:Int,
    @SerializedName("active")
    var active:String,
    @SerializedName("price")
    var price:Int): Serializable
