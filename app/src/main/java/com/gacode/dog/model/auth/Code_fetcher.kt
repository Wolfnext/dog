package com.gacode.dog.model.ActiveAccount

import android.content.Context
import com.gacode.dog.R
import com.gacode.dog.api.API_Settings

import com.gacode.dog.api.auth.auth_api
import com.gacode.dog.api.base.API_creator
import com.gacode.dog.model.auth.Code
import com.gacode.dog.model.token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Code_fetcher {

    class ActiveAccountFetcherImpl(private val context: Context,
                          private val listener: Listener){

        private var callback: Call<Any>? = null

        fun activeAccount(code: Code, uid: String) {
            val ActiveAccountFetcher = API_creator(auth_api::class.java, API_Settings.base).generate()
            callback = ActiveAccountFetcher.activeAccount(code,uid)
            callback?.enqueue(object : Callback<Any> {

                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    if(response != null){
                        if(response.isSuccessful){
                            listener.onSuccess()
                        }else{
                            listener.onError(Throwable(context.getString(R.string.ActiveAccount_error)))
                        }
                    }else{
                        listener.onError(Throwable(context.getString(R.string.ActiveAccount_error)))
                    }
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    val msg = context.getString(R.string.error_activation)
                    listener.onError(Throwable("$msg : ${t?.message}"))
                }
            })
        }

        fun sendCode(uid: String) {
            val ActiveAccountFetcher = API_creator(auth_api::class.java, API_Settings.base).generate()
            callback = ActiveAccountFetcher.sendCode(uid)
            callback?.enqueue(object : Callback<Any> {


                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    if(response.isSuccessful) {
                        listener.onSuccess()
                    }
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    val msg = context.getString(R.string.sendCode_error)
                    listener.onError(Throwable("$msg : ${t?.message}"))
                }
            })
        }

        fun cancel(){
            callback?.cancel()
        }
    }

    interface Listener {
        fun onSuccess()
        fun onError(throwable: Throwable)
    }
    
    
}