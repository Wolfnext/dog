package com.gacode.dog.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.sql.Timestamp
import java.util.*

data class Dog(
                   @SerializedName("id")
                   val id:Int,
                   @SerializedName("name")
                   var name:String,
                   @SerializedName("race")
                   var race:String,
                   @SerializedName("birth")
                   var birth:String,
                   @SerializedName("desc")
                   var desc:String,
                   @SerializedName("gender")
                   var gender:String,
                   @SerializedName("createdAt")
                   val createdAt:String,
                    @SerializedName("size")
                   var size:String): Serializable

