package com.gacode.dog.model

import java.io.Serializable

data class token(val scope: String,
                 val expire_date:Long,
                 val refresh_token:String,
                 val access_token:String):Serializable

