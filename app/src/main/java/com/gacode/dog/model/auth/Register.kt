package com.gacode.dog.model.auth

import java.io.Serializable

data class Register(
    val email: String,
    val password: String, val type: String
) : Serializable

