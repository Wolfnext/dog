package com.gacode.dog.view.search

import android.content.Context
import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView
import com.gacode.dog.model.Profile
import com.gacode.dog.model.Search
import com.gacode.dog.view.profile.ProfileActivity

object SearchContract {
    interface SearchView : BaseMVPView {
        fun onLogout()
        fun onSuccess(search: ArrayList<Search>)
        fun onSuccessArray(search : ArrayList<Search>)
        fun onFailed(e: String)
        fun onError(e: Throwable)
    }
    interface SearchPresenter : BaseMVPPresenter<SearchView> {
        fun getSearch(context: Context, search: Search)
        fun cancel()

    }
}