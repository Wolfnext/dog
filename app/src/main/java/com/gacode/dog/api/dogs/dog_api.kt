package com.gacode.dog.api.dogs

import com.gacode.dog.model.Dog
import com.gacode.dog.model.Profile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface dog_api {

    /**
     * Endpoint of get data Profile
     */
    @GET("dogs")
    fun getDogs(@Header("Authorization") token: String) : Call<ArrayList<Dog>>

}