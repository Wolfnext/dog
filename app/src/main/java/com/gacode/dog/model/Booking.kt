package com.gacode.dog.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.sql.Timestamp

data class Booking(
    @SerializedName("owner_firstname")
    var owner_firstname:String,
    @SerializedName("sitter_firstname")
    var sitter_firstname:String,
    @SerializedName("dog_name")
    var dog_name:String,
    @SerializedName("dogId")
    var dogId:String,
    @SerializedName("sitterId")
    var sitterId:String,
    @SerializedName("ownerId")
    var ownerId:String,
    @SerializedName("hashId")
    var hashId:String,
    @SerializedName("title")
    var title:String,
    @SerializedName("description")
    var description:String,
    @SerializedName("lat")
    val lat:Double,
    @SerializedName("lon")
    var lon:Double,
    @SerializedName("city")
    var city:String,
    @SerializedName("time_start")
    val time_start:Timestamp,
    @SerializedName("time_end")
    val time_end:Timestamp,
    @SerializedName("status")
    val status:String,
    @SerializedName("cancelReaseon")
    val cancelReaseon:String,
    @SerializedName("price")
    val price:Int): Serializable

