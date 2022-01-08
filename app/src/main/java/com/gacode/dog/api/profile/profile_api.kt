package com.gacode.dog.api.profile

import com.gacode.dog.model.Profile
import com.gacode.dog.model.auth.Auth
import com.gacode.dog.model.token
import retrofit2.Call
import retrofit2.http.*

interface profile_api {

    /**
     * Endpoint of get data Profile
     */
    @GET("profile/{uid}")
    fun getProfile(@Path("uid") uid: String) : Call<Profile>

    /**
     * Endpoint of Update data Profile
     */
    @PATCH("profile")
    fun getProfile(@Body profile: Profile) : Call<Profile>


}