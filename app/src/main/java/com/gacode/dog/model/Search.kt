package com.gacode.dog.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Search(
    @SerializedName("type")
    var type: String,
    @SerializedName("datetime_start")
    var datetime_start:String,
    @SerializedName("datetime_end")
    var datetime_end:String,
    @SerializedName("lat")
    var lat:Double,
    @SerializedName("lon")
    var lon:Double,
    @SerializedName("radius")
    var radius:String,
    @SerializedName("price")
    var price:Int,
    @SerializedName("price_start")
    var price_start:Int,
    @SerializedName("price_end")
    var price_end:Int,
    @SerializedName("firstname")
    var firstname:String,
    @SerializedName("desc")
    var desc:String,
    @SerializedName("distance")
    var distance:Double,
    @SerializedName("size_dog")
    val size_dog:Int): Serializable

