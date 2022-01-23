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

        private var callbackArrayDogs: Call<ArrayList<Dog>>? = null
        private var callbackDog: Call<Dog>? = null
        private var callbackAny: Call<Any>? = null

        fun getDog(context: Context) {
            val getProfileFetcher = API_creator(dog_api::class.java, API_Settings.base).generate()

            callbackArrayDogs = getProfileFetcher.getDogs(token = "Bearer " + Authentication.getAccessToken(context))
            callbackArrayDogs?.enqueue(object : Callback<ArrayList<Dog>> {
                override fun onResponse(call: Call<ArrayList<Dog>>, response: Response<ArrayList<Dog>>) {
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
                        listener.onError(Throwable(this@DogFetcherImpl.context.getString(R.string.auth_error)))
                    }
                }

                override fun onFailure(call: Call<ArrayList<Dog>>?, t: Throwable?) {
                    Log.d("response","failure")

                }
            })
        }


        fun createDog(context: Context, dog : Dog) {
            val dogFetcher = API_creator(dog_api::class.java, API_Settings.base).generate()

            callbackDog = dogFetcher.createDog(token = "Bearer " + Authentication.getAccessToken(context), dog)
            callbackDog?.enqueue(object : Callback<Dog> {
                override fun onResponse(call: Call<Dog>, response: Response<Dog>) {
                    Log.d("response",response.body().toString())
                    if (response != null) {
                        if (response.isSuccessful) {
                            Log.d("response",response.body().toString())
                            response.body()?.let { listener.onSuccessDog(it) }
                        } else {
                            Log.d("response",response.body().toString())
                            listener.onSuccessDog(null)
                        }
                    } else {
                        Log.d("response","error")
                        listener.onError(Throwable(this@DogFetcherImpl.context.getString(R.string.auth_error)))
                    }
                }

                override fun onFailure(call: Call<Dog>?, t: Throwable?) {
                    Log.d("response","failure")

                }
            })
        }


        fun updateDog(context: Context, id: Int, dog: Dog) {
            val dogFetcher = API_creator(dog_api::class.java, API_Settings.base).generate()

            callbackDog = dogFetcher.updateDog(token = "Bearer " + Authentication.getAccessToken(context), id, dog)
            callbackDog?.enqueue(object : Callback<Dog> {
                override fun onResponse(call: Call<Dog>, response: Response<Dog>) {

                    if (response != null) {
                        if (response.isSuccessful) {
                            Log.d("response",response.body().toString())
                            response.body()?.let { listener.onSuccessDog(it) }
                        } else {
                            Log.d("response",response.body().toString())
                            listener.onSuccessDog(null)
                        }
                    } else {
                        Log.d("response","error")
                        listener.onError(Throwable(this@DogFetcherImpl.context.getString(R.string.auth_error)))
                    }
                }

                override fun onFailure(call: Call<Dog>?, t: Throwable?) {
                    Log.d("response","failure")

                }
            })
        }

        fun deleteDog(context: Context, id :Int) {
            val dogFetcher = API_creator(dog_api::class.java, API_Settings.base).generate()

            callbackAny = dogFetcher.deleteDog(token = "Bearer " + Authentication.getAccessToken(context), id)
            callbackAny?.enqueue(object : Callback<Any>  {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    Log.d("response",response.body().toString())
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
                        listener.onError(Throwable(this@DogFetcherImpl.context.getString(R.string.auth_error)))
                    }
                }

                override fun onFailure(call: Call<Any>?, t: Throwable?) {
                    Log.d("response","failure")

                }
            })
        }




        fun cancel() {
            callbackDog?.cancel()
            callbackArrayDogs?.cancel()
        }


    }


    interface Listener {
        fun onSuccess()
        fun onSuccessDog(dog: Dog?)
        fun onSuccessArray(dog: ArrayList<Dog>?)
        fun onError(throwable: Throwable)
    }
}