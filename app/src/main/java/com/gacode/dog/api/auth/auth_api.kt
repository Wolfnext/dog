package com.gacode.dog.api.auth

import com.gacode.dog.model.auth.Auth
import com.gacode.dog.model.auth.Refresh
import com.gacode.dog.model.token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface auth_api {

    /**
     * Your endpoint of auth
     */
    @POST("auth")
    fun auth(@Body auth: Auth): Call<token>

    /**
     * Your endpoint of refresh your token
     */
    @POST("auth/refresh")
    fun refresh(@Body refreshAuth: Refresh): Call<token>
}