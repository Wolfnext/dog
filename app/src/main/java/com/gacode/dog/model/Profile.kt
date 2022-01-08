package com.gacode.dog.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Profile(
    @SerializedName("uid")
    val uid:String,
@SerializedName("firstname")
val firstname: String,
@SerializedName("surname")
var surname:String,
@SerializedName("place")
val place:Object,
@SerializedName("phone")
val phone:Number,
@SerializedName("desc")
var desc:String,
@SerializedName("photoURL")
val photoUrl:String):Serializable


