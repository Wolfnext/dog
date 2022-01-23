package com.gacode.dog.view.splash

import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.model.auth.Refresh
import com.gacode.dog.model.auth.Refresh_fetcher
import com.gacode.dog.model.token
import com.gacode.dog.util.Authentication

class SplashPresenterImpl : BaseMVPPresenterImpl<SplashContract.SplashView>(),
    SplashContract.SplashPresenter {

    private var refreshFetcher: Refresh_fetcher.RefreshFetcherImpl? = null

    override fun isAuthenticated() {
        try {

            if(Authentication.isAuthenticated(getContext())){
                view?.let { view -> call(view, view::onSuccess) }
            } else {
                view?.let { view -> call(view,
                    getContext().getString(R.string.authenticating),
                    view::onMessage)
                }
                refreshFetcher = Refresh_fetcher.RefreshFetcherImpl(getContext(),
                    object : Refresh_fetcher.Listener {

                        override fun onSuccess(token: token?) {
                            if(token == null){
                                view?.let { view -> call(view, view::onLogin) }
                            }else{

                                Authentication.save(getContext(), token)
                                view?.let { view -> call(view, view::onSuccess) }
                            }
                        }

                        override fun onError(throwable: Throwable) {
                            view?.let { view -> call(view, throwable, view::onError) }
                        }
                    })
                refreshFetcher?.refresh(Refresh(Authentication.getRefresh(getContext())))
            }
        } catch (e: Authentication.WithoutAuthenticatedException) {
            view?.let { view -> call(view, view::onLogin) }
        }
    }

    override fun cancel() {
        refreshFetcher?.cancel()
    }
}