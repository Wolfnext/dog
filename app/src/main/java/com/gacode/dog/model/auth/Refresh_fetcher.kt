package com.gacode.dog.model.auth

import android.content.Context
import com.gacode.dog.R
import com.gacode.dog.api.API_Settings
import com.gacode.dog.api.auth.auth_api
import com.gacode.dog.api.base.API_creator
import com.gacode.dog.model.token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object refresh_Fetcher {

    class RefreshFetcherImpl(private val context: Context,
                             private val listener: Listener){

        private var callback: Call<token>? = null

        fun refresh(refresh: Refresh) {
            val authFetcher = API_creator(auth_api::class.java,API_Settings.base).generate()
            callback = authFetcher.refresh(refresh)
            callback?.enqueue(object : Callback<token> {

                override fun onResponse(call: Call<token>?, response: Response<token>?) {
                    if(response != null){
                        if(response.isSuccessful){
                            listener.onSuccess(response.body())
                        }else{
                            listener.onSuccess(null)
                        }
                    }else{
                        listener.onError(Throwable(context.getString(R.string.auth_error)))
                    }
                }

                override fun onFailure(call: Call<token>?, t: Throwable?) {
                    val msg = context.getString(R.string.auth_error)
                    listener.onError(Throwable("$msg : ${t?.message}"))
                }
            })
        }

        fun cancel(){
            callback?.cancel()
        }
    }

    interface Listener {
        fun onSuccess(token: token?)
        fun onError(throwable: Throwable)
    }
}