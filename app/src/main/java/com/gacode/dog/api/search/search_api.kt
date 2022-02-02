package com.gacode.dog.api.search


import com.gacode.dog.model.Profile
import com.gacode.dog.model.Search
import retrofit2.Call
import retrofit2.http.*

interface search_api {

    /**
     * Endpoint of get search sitters data
     */
    @POST("sitters/search")
    fun getSearchs(@Header("Authorization") token: String, @Body search: Search) : Call<ArrayList<Search>>

}