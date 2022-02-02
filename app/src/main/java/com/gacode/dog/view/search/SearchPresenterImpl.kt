package com.gacode.dog.view.search

import android.content.Context
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.model.Profile
import com.gacode.dog.model.Profile_fetcher
import com.gacode.dog.model.Search
import com.gacode.dog.model.Search_fetcher
import com.gacode.dog.util.Authentication
import com.gacode.dog.view.profile.ProfileActivity

class SearchPresenterImpl : BaseMVPPresenterImpl<SearchContract.SearchView>(),
    SearchContract.SearchPresenter {


    private var SearchFetcher: Search_fetcher.SearchFetcherImpl? = null

    override fun getSearch(context: Context, search: Search) {

        SearchFetcher = Search_fetcher.SearchFetcherImpl(context, object : Search_fetcher.Listener {


            override fun onSuccessSearch(Search: Search?) {
                TODO("Not yet implemented")
            }

            override fun onSuccessArray(Search: ArrayList<Search>?) {
                if (Search == null) {
                    view?.let { view ->
                        call(
                            view,
                            context.getString(R.string.getSearch_error),
                            view::onFailed
                        )
                    }
                } else {
                    view?.let { view -> call(view, Search, view::onSuccess) }
                }

            }

            override fun onError(throwable: Throwable) {

                view?.let { view -> call(view, throwable, view::onError) }
            }
        })

        SearchFetcher?.getSearch(context,search)
    }




    override fun cancel() {
        SearchFetcher?.cancel()
    }

}
