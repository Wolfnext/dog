package com.gacode.dog.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Profile(
   // @SerializedName("uid")
   // val uid:String,
@SerializedName("firstname")
var firstname: String,
@SerializedName("surname")
var surname:String,
@SerializedName("lon")
var lon:Double,
@SerializedName("lat")
var lat:Double,
@SerializedName("city")
var city:String,
@SerializedName("phone")
var phone:String,
@SerializedName("desc")
var desc:String,
@SerializedName("photoURL")
val photoUrl:String):Serializable


