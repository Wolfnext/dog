package com.gacode.dog.api.dogs

import com.gacode.dog.model.Dog
import com.gacode.dog.model.Profile
import com.gacode.dog.model.auth.Register
import retrofit2.Call
import retrofit2.http.*

interface dog_api {

    /**
     * Endpoint of get data Dogs
     */
    @GET("dogs")
    fun getDogs(@Header("Authorization") token: String) : Call<ArrayList<Dog>>

    /**
     * Endpoint of create Dog
     */
    @POST("dogs")
    fun createDog(@Header("Authorization") token: String, @Body dog: Dog) : Call<Dog>

    /**
     * Endpoint of update Dog
     */
    @PATCH("dogs/{id}")
    fun updateDog(@Header("Authorization") token: String, @Path("id") id: Int, @Body dog: Dog) : Call<Dog>

    /**
     * Endpoint of delete Dog
     */
    @DELETE("dogs/{id}")
    fun deleteDog(@Header("Authorization") token: String, @Path("id") id: Int) : Call<Any>

}