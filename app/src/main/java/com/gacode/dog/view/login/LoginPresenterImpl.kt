package com.gacode.dog.view.login

import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.model.auth.Auth
import com.gacode.dog.model.auth.Auth_fetcher
import com.gacode.dog.model.token
import com.gacode.dog.util.Authentication

class LoginPresenterImpl : BaseMVPPresenterImpl<LoginContract.LoginView>(),
    LoginContract.LoginPresenter {

    private var authFetcher: Auth_fetcher.AuthFetcherImpl?= null

    override fun login(email: String, password: String) {
        authFetcher = Auth_fetcher.AuthFetcherImpl(getContext(), object : Auth_fetcher.Listener {
            override fun onSuccess(token: token?) {
                if(token == null) {
                    view?.let { view -> call(view,
                        getContext().getString(R.string.auth_invalid),
                        view::onFailed)
                    }
                } else {
                    Authentication.save(getContext(), token)
                    view?.let { view -> call(view, view::onSuccess)}
                }
            }
            override fun onError(throwable: Throwable) {
                view?.let { view -> call(view, throwable, view::onError) }
            }
        })
        authFetcher?.auth(Auth(email, password))
    }

    override fun cancel() {
        authFetcher?.cancel()
    }
}