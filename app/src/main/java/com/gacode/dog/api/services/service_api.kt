package com.gacode.dog.api.services

import com.gacode.dog.model.Service

import retrofit2.Call
import retrofit2.http.*

interface service_api {


    /**
     * Endpoint of get services data
     */
    @GET("services")
    fun getServices(@Header("Authorization") token: String) : Call<ArrayList<Service>>


    /**
     * Endpoint of udpate service
     */
    @PATCH("services")
    fun updateService(@Header("Authorization") token: String, @Body services: Service) : Call<Service>

}