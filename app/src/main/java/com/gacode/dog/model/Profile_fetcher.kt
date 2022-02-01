package com.gacode.dog.model

import android.content.Context
import android.util.Log
import com.gacode.dog.R
import com.gacode.dog.api.API_Settings
import com.gacode.dog.api.base.API_creator
import com.gacode.dog.api.profile.profile_api
import com.gacode.dog.util.Authentication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Profile_fetcher {
    class ProfileFetcherImpl(
        private val context: Context,
        private val listener: Profile_fetcher.Listener
    ) {

        private var callback: Call<Profile>? = null

        fun getProfile(context: Context) {
            val getProfileFetcher = API_creator(profile_api::class.java, API_Settings.base).generate()

            callback = getProfileFetcher.getProfile(token = "Bearer " + Authentication.getAccessToken(context))
            callback?.enqueue(object : Callback<Profile> {
                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
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
                        listener.onError(Throwable(this@ProfileFetcherImpl.context.getString(R.string.auth_error)))
                    }
                }

                override fun onFailure(call: Call<Profile>?, t: Throwable?) {
                    Log.d("response","failure")

                }
            })
        }


        fun updateProfile(context: Context, profile :Profile) {
            val getProfileFetcher = API_creator(profile_api::class.java, API_Settings.base).generate()

            callback = getProfileFetcher.updateProfile(token = "Bearer " + Authentication.getAccessToken(context), profile)
            callback?.enqueue(object : Callback<Profile> {
                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
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
                        listener.onError(Throwable(this@ProfileFetcherImpl.context.getString(R.string.auth_error)))
                    }
                }

                override fun onFailure(call: Call<Profile>?, t: Throwable?) {
                    Log.d("response","failure")

                }
            })
        }


        


        fun cancel() {
            callback?.cancel()
        }
    }


    interface Listener {
        fun onSuccess(profile: Profile?)
        fun onError(throwable: Throwable)
    }
}