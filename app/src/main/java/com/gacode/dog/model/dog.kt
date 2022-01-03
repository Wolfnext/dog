package com.gacode.dog.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class dog(
                   @SerializedName("uid")
                   val uid: String,
                   @SerializedName("name")
                   var name:String,
                   @SerializedName("race")
                   val race:String,
                   @SerializedName("birth")
                   val birth:Date): Serializable

