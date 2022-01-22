package com.gacode.dog.view.auth.register

import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.model.auth.Register
import com.gacode.dog.model.auth.Register_fetcher
import com.gacode.dog.model.user
import com.gacode.dog.util.Authentication


class RegisterPresenterImpl : BaseMVPPresenterImpl<RegisterContract.RegisterView>(),
    RegisterContract.RegisterPresenter {

    private var registerFetcher: Register_fetcher.RegisterFetcher?= null

    override fun register(email: String, password: String, type: String) {
        registerFetcher = Register_fetcher.RegisterFetcher(getContext(), object : Register_fetcher.Listener {
            override fun onSuccess(user: user?) {
                if(user == null) {
                    view?.let { view -> call(view,
                        getContext().getString(R.string.register_invalid),
                        view::onFailed)
                    }
                } else {
                    Authentication.save(getContext(), user)
                    view?.let { view -> call(view, view::onSuccess)}
                }
            }
            override fun onError(throwable: Throwable) {
                view?.let { view -> call(view, throwable, view::onError) }
            }
        })
        registerFetcher?.register(Register(email, password, type))
    }

    override fun cancel() {
        registerFetcher?.cancel()
    }

    override fun openActivation() {
        view?.let { view -> call(view, view::onSuccess)}
    }
    override fun openLogin(){
        view?.let { view -> call(view, view::onLogin)}
    }
}