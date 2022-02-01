package com.gacode.dog.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cancel_Reason(
    @SerializedName("cancelReaseon")
    var cancelReaseon:String): Serializable
