package com.gacode.dog.api.bookings

import com.gacode.dog.model.auth.Auth
import com.gacode.dog.model.auth.Refresh
import com.gacode.dog.model.token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface booking_api {

    /**
     * Your endpoint of auth
     */
   // @GET("bookings")
    //fun auth(@Body auth: Auth): Call<token>

    /**
     * Your endpoint of refresh your token
     */
    //@POST("/bookings/history")
    //fun refresh(@Body refreshAuth: Refresh): Call<token>
}
