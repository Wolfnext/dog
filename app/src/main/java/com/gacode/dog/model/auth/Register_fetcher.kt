package com.gacode.dog.model.auth

import android.content.Context
import com.gacode.dog.R
import com.gacode.dog.api.API_Settings
import com.gacode.dog.api.auth.auth_api
import com.gacode.dog.api.base.API_creator
import com.gacode.dog.model.user
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Register_fetcher {

    class RegisterFetcher(private val context: Context,
                       private val listener: Register_fetcher.Listener
    ){

    private var callback: Call<user>? = null

    fun register(register: Register) {
        val registerFetcher = API_creator(auth_api::class.java, API_Settings.base).generate()
        callback = registerFetcher.register(register)
        callback?.enqueue(object : Callback<user> {

            override fun onResponse(call: Call<user>?, response: Response<user>?) {
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

            override fun onFailure(call: Call<user>?, t: Throwable?) {
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
    fun onSuccess(user: user?)
    fun onError(throwable: Throwable)
}
}