package com.gacode.dog.api.bookings

import com.gacode.dog.model.Booking
import com.gacode.dog.model.Cancel_Reason
import retrofit2.Call
import retrofit2.http.*

interface booking_api {

    /**
     * Endpoint of get data Bookings
     */
    @GET("bookings")
    fun getBookings(@Header("Authorization") token: String) : Call<ArrayList<Booking>>

    /**
     * Endpoint of Create a booking
     */
    @PATCH("bookings")
    fun createBooking(@Header("Authorization") token: String, @Body booking: Booking) : Call<Booking>

     /**
     * Endpoint of cancel booking
     */
    @POST("bookings/{id}/cancel")
    fun cancelBooking(@Header("Authorization") token: String, @Body cancel_reason: Cancel_Reason, @Path("id") id: Int) : Call<Any>

    /**
     * Endpoint of Create a booking
     */
    @POST("bookings/{id}/confirm")
    fun confirmBooking(@Header("Authorization") token: String, @Path("id") id: Int) : Call<Any>

}
