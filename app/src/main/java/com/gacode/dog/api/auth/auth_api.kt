package com.gacode.dog.api.auth

import com.gacode.dog.model.auth.Auth
import com.gacode.dog.model.auth.Code
import com.gacode.dog.model.auth.Refresh
import com.gacode.dog.model.auth.Register
import com.gacode.dog.model.token
import com.gacode.dog.model.user
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface auth_api {

    /**
     * Endpoint of auth
     */
    @POST("auth")
    fun auth(@Body auth: Auth): Call<token>

    /**
     * Endpoint of refresh your token
     */
    @POST("auth/refresh")
    fun refresh(@Body refreshAuth: Refresh): Call<token>

    /**
     *  endpoint of register
     */
    @POST("auth/register")
    fun register(@Body register: Register): Call<user>

    /**
    *  endpoint of send code activation
    */
    @GET("users/{uid}/confirm-registration")
    fun sendCode() : Call<Any>

    /**
    *  endpoint of confirm account
    */
    @POST("users/{uid}/confirm-registration")
    fun activeAccount(@Body code: Code, @Path("uid") uid: String) : Call<Any>

}