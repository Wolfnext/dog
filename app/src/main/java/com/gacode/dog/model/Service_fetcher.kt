package com.gacode.dog.model

import android.content.Context
import android.util.Log
import com.gacode.dog.R
import com.gacode.dog.api.API_Settings
import com.gacode.dog.api.base.API_creator
import com.gacode.dog.api.services.service_api
import com.gacode.dog.util.Authentication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Service_fetcher {
    class ServiceFetcherImpl(
        private val context: Context,
        private val listener: Service_fetcher.Listener
    ) {

        private var callbackArrayServices: Call<ArrayList<Service>>? = null
        private var callbackService: Call<Service>? = null
        private var callbackAny: Call<Any>? = null

        fun getService(context: Context) {
            val getProfileFetcher = API_creator(service_api::class.java, API_Settings.base).generate()

            callbackArrayServices = getProfileFetcher.getServices(token = "Bearer " + Authentication.getAccessToken(context))
            callbackArrayServices?.enqueue(object : Callback<ArrayList<Service>> {
                override fun onResponse(call: Call<ArrayList<Service>>, response: Response<ArrayList<Service>>) {
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
                        listener.onError(Throwable(this@ServiceFetcherImpl.context.getString(R.string.auth_error)))
                    }
                }

                override fun onFailure(call: Call<ArrayList<Service>>?, t: Throwable?) {
                    Log.d("response","failure")

                }
            })
        }



        fun updateService(context: Context, service: Service) {
            val ServiceFetcher = API_creator(service_api::class.java, API_Settings.base).generate()

            callbackService = ServiceFetcher.updateService(token = "Bearer " + Authentication.getAccessToken(context), service)
            callbackService?.enqueue(object : Callback<Service> {
                override fun onResponse(call: Call<Service>, response: Response<Service>) {

                    if (response != null) {
                        if (response.isSuccessful) {
                            Log.d("response",response.body().toString())
                            response.body()?.let { listener.onSuccessService(it) }
                        } else {
                            Log.d("response",response.body().toString())
                            listener.onSuccessService(null)
                        }
                    } else {
                        Log.d("response","error")
                        listener.onError(Throwable(this@ServiceFetcherImpl.context.getString(R.string.auth_error)))
                    }
                }

                override fun onFailure(call: Call<Service>?, t: Throwable?) {
                    Log.d("response","failure")

                }
            })
        }


        fun cancel() {
            callbackService?.cancel()
            callbackArrayServices?.cancel()
        }


    }


    interface Listener {
        fun onSuccessService(Service: Service?)
        fun onSuccessArray(Service: ArrayList<Service>?)
        fun onError(throwable: Throwable)
    }
}