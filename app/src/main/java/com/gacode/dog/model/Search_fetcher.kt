package com.gacode.dog.model

import android.content.Context
import android.util.Log
import com.gacode.dog.R
import com.gacode.dog.api.API_Settings
import com.gacode.dog.api.base.API_creator
import com.gacode.dog.api.search.search_api
import com.gacode.dog.api.services.service_api
import com.gacode.dog.util.Authentication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Search_fetcher {
    class SearchFetcherImpl(
        private val context: Context,
        private val listener: Search_fetcher.Listener
    ) {

        private var callbackArraySearchs: Call<ArrayList<Search>>? = null
        private var callbackSearch: Call<Search>? = null
        private var callbackAny: Call<Any>? = null

        fun getSearch(context: Context, search: Search) {
            val getProfileFetcher = API_creator(search_api::class.java, API_Settings.base).generate()

            callbackArraySearchs = getProfileFetcher.getSearchs(token = "Bearer " + Authentication.getAccessToken(context), search)
            callbackArraySearchs?.enqueue(object : Callback<ArrayList<Search>> {
                override fun onResponse(call: Call<ArrayList<Search>>, response: Response<ArrayList<Search>>) {
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
                        listener.onError(Throwable(this@SearchFetcherImpl.context.getString(R.string.auth_error)))
                    }
                }

                override fun onFailure(call: Call<ArrayList<Search>>?, t: Throwable?) {
                    Log.d("response","failure")

                }
            })
        }


        fun cancel() {
            callbackSearch?.cancel()
            callbackArraySearchs?.cancel()
        }


    }


    interface Listener {
        fun onSuccessSearch(Search: Search?)
        fun onSuccessArray(Search: ArrayList<Search>?)
        fun onError(throwable: Throwable)
    }
}