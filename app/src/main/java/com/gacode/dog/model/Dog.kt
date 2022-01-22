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
                   val race:String,
                   @SerializedName("birth")
                   val birth:String,
                   @SerializedName("desc")
                   val desc:String,
                   @SerializedName("gender")
                   val gender:String,
                   @SerializedName("createdAt")
                   val createdAt:String,
                    @SerializedName("size")
                    val size:String): Serializable

