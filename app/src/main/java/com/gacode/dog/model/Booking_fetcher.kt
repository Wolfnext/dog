package com.gacode.dog.model

import android.content.Context
import android.util.Log
import com.gacode.dog.R
import com.gacode.dog.api.API_Settings
import com.gacode.dog.api.base.API_creator
import com.gacode.dog.api.bookings.booking_api
import com.gacode.dog.util.Authentication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Booking_fetcher {
    class BookingFetcherImpl(
        private val context: Context,
        private val listener: Booking_fetcher.Listener
    ) {

        private var callbackArrayBookings: Call<ArrayList<Booking>>? = null
        private var callbackBooking: Call<Booking>? = null
        private var callbackAny: Call<Any>? = null

        fun getBooking(context: Context) {
            val getProfileFetcher = API_creator(booking_api::class.java, API_Settings.base).generate()

            callbackArrayBookings = getProfileFetcher.getBookings(token = "Bearer " + Authentication.getAccessToken(context))
            callbackArrayBookings?.enqueue(object : Callback<ArrayList<Booking>> {
                override fun onResponse(call: Call<ArrayList<Booking>>, response: Response<ArrayList<Booking>>) {
                    Log.d("response",response.body().toString())
                    if (response != null) {
                        if (response.isSuccessful) {
                            Log.d("response",response.body().toString())
                            response.body()?.let { listener.onSuccessArray(it) }
                        } else {
                            Log.d("response",response.body().toString())
                            listener.onSuccessArray(null)
                        }
                    } else {
                        Log.d("response","error")
                        listener.onError(Throwable(this@BookingFetcherImpl.context.getString(R.string.auth_error)))
                    }
                }

                override fun onFailure(call: Call<ArrayList<Booking>>?, t: Throwable?) {
                    Log.d("response","failure")

                }
            })
        }


        fun createBooking(context: Context, Booking : Booking) {
            val BookingFetcher = API_creator(booking_api::class.java, API_Settings.base).generate()

            callbackBooking = BookingFetcher.createBooking(token = "Bearer " + Authentication.getAccessToken(context), Booking)
            callbackBooking?.enqueue(object : Callback<Booking> {
                override fun onResponse(call: Call<Booking>, response: Response<Booking>) {
                    Log.d("response",response.body().toString())
                    if (response != null) {
                        if (response.isSuccessful) {
                            Log.d("response",response.body().toString())
                            response.body()?.let { listener.onSuccessBooking(it) }
                        } else {
                            Log.d("response",response.body().toString())
                            listener.onSuccessBooking(null)
                        }
                    } else {
                        Log.d("response","error")
                        listener.onError(Throwable(this@BookingFetcherImpl.context.getString(R.string.auth_error)))
                    }
                }

                override fun onFailure(call: Call<Booking>?, t: Throwable?) {
                    Log.d("response","failure")

                }
            })
        }


        fun cancelBooking(context: Context, id: Int, cancel_reason: Cancel_Reason) {
            val BookingFetcher = API_creator(booking_api::class.java, API_Settings.base).generate()

            callbackAny = BookingFetcher.cancelBooking(token = "Bearer " + Authentication.getAccessToken(context), cancel_reason, id)
            callbackAny?.enqueue(object : Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {

                    if (response != null) {
                        if (response.isSuccessful) {
                            Log.d("response",response.body().toString())
                           listener.onSuccess()
                        } else {
                            Log.d("response",response.body().toString())
                            listener.onSuccess()
                        }
                    } else {
                        Log.d("response","error")
                        listener.onError(Throwable(this@BookingFetcherImpl.context.getString(R.string.auth_error)))
                    }
                }


                override fun onFailure(call: Call<Any>, t: Throwable) {

                }
            })
        }

        fun cancel() {
            callbackBooking?.cancel()
            callbackArrayBookings?.cancel()
        }


    }


    interface Listener {
        fun onSuccess()
        fun onSuccessBooking(Booking: Booking?)
        fun onSuccessArray(Booking: ArrayList<Booking>?)
        fun onError(throwable: Throwable)
    }
}