package com.gacode.dog.model

import android.content.Context
import android.util.Log
import com.gacode.dog.R
import com.gacode.dog.api.API_Settings
import com.gacode.dog.api.base.API_creator
import com.gacode.dog.api.dogs.dog_api
import com.gacode.dog.util.Authentication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Dog_fetcher {
    class DogFetcherImpl(
        private val context: Context,
        private val listener: Dog_fetcher.Listener
    ) {

        private var callback: Call<ArrayList<Dog>>? = null

        fun getProfile(context: Context) {
            val getProfileFetcher = API_creator(dog_api::class.java, API_Settings.base).generate()

            callback = getProfileFetcher.getDogs(token = "Bearer " + Authentication.getAccessToken(context))
            callback?.enqueue(object : Callback<ArrayList<Dog>> {
                override fun onResponse(call: Call<ArrayList<Dog>>, response: Response<ArrayList<Dog>>) {
                    Log.d("response",response.body().toString())
                    if (response != null) {
                        if (response.isSuccessful) {
                            Log.d("response",response.body().toString())
                            response.body()?.let { listener.onSuccess(it) }
                        } else {
                            Log.d("response",response.body().toString())
                            listener.onSuccess(null)
                        }
                    } else {
                        Log.d("response","error")
                        listener.onError(Throwable(this@DogFetcherImpl.context.getString(R.string.auth_error)))
                    }
                }

                override fun onFailure(call: Call<ArrayList<Dog>>?, t: Throwable?) {
                    Log.d("response","failure")

                }
            })
        }


        fun cancel() {
            callback?.cancel()
        }
    }


    interface Listener {
        fun onSuccess(dog: ArrayList<Dog>?)
        fun onError(throwable: Throwable)
    }
}