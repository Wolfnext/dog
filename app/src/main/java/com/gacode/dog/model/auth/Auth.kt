package com.gacode.dog.model.auth

import java.io.Serializable

data class Auth(val email: String,
                val pass: String) : Serializable