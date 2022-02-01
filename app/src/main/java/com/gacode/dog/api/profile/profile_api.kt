package com.gacode.dog.api.profile

import com.gacode.dog.model.Profile
import retrofit2.Call
import retrofit2.http.*

interface profile_api {

    /**
     * Endpoint of get data Profile
     */
    @GET("profile")
    fun getProfile(@Header("Authorization") token: String) : Call<Profile>

    /**
     * Endpoint of Update data Profile
     */
    @PATCH("profile")
    fun updateProfile(@Header("Authorization") token: String, @Body profile: Profile) : Call<Profile>


}