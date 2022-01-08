package com.gacode.dog.model

import android.content.Context
import com.gacode.dog.R
import com.gacode.dog.api.API_Settings
import com.gacode.dog.api.base.API_creator
import com.gacode.dog.api.profile.profile_api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Profile_fetcher {
    class ProfileFetcherImpl(
        private val context: Context,
        private val listener: Profile_fetcher.Listener
    ) {

        private var callback: Call<Profile>? = null

        fun getProfile(context: Context, uid: String?) {
            val getProfileFetcher =
                API_creator(profile_api::class.java, API_Settings.base).generate()
            callback = uid?.let { getProfileFetcher.getProfile(it) }!!
            callback?.enqueue(object : Callback<Profile> {

                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                    if (response != null) {
                        if (response.isSuccessful) {
                            response.body()?.let { listener.onSuccess(it) }
                        } else {
                            listener.onSuccess(null)
                        }
                    } else {
                        listener.onError(Throwable(this@ProfileFetcherImpl.context.getString(R.string.auth_error)))
                    }
                }

                override fun onFailure(call: Call<Profile>?, t: Throwable?) {
                    val msg = this@ProfileFetcherImpl.context.getString(R.string.auth_error)
                    listener.onError(Throwable("$msg : ${t?.message}"))
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