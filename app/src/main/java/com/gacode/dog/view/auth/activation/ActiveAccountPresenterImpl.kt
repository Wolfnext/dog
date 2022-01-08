package com.gacode.dog.view.auth.activation

import android.util.Log
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.model.ActiveAccount.Code_fetcher
import com.gacode.dog.model.auth.Auth
import com.gacode.dog.model.auth.Auth_fetcher
import com.gacode.dog.model.auth.Code
import com.gacode.dog.model.token
import com.gacode.dog.util.Authentication


class ActiveAccountPresenterImpl : BaseMVPPresenterImpl<ActiveAccountContract.ActiveAccountView>(),
    ActiveAccountContract.ActiveAccountPresenter {

    private var activeAccountFetcher: Code_fetcher.ActiveAccountFetcherImpl? = null

    private var sendCodeFetcher: Code_fetcher.ActiveAccountFetcherImpl? = null

    override fun activeAccount(code: String, uid: String?) {
        activeAccountFetcher =
            Code_fetcher.ActiveAccountFetcherImpl(getContext(), object : Code_fetcher.Listener {
                override fun onSuccess() {
                    //Authentication.save(getContext(), token)
                    view?.let { view -> call(view,
                        view::onSuccess)}

                }



                override fun onError(throwable: Throwable) {
                    view?.let { view -> call(view,
                        getContext().getString(R.string.error_activation),
                        view::onFailed)
                    }
                }
            })
        if (uid != null) {
            activeAccountFetcher?.activeAccount(Code(code), uid)
        }
    }


    override fun sendCode(uid: String?) {
        sendCodeFetcher = Code_fetcher.ActiveAccountFetcherImpl(getContext(), object : Code_fetcher.Listener {
            override fun onSuccess() {
                view?.let { view -> call(view,
                    getContext().getString(R.string.sendCodeSuccess),
                    view::onFailed)
                }
            }

            override fun onError(throwable: Throwable) {
                view?.let { view -> call(view,
                    getContext().getString(R.string.sendCode_error),
                    view::onFailed)
                }
            }
        })
        if (uid != null) {
            sendCodeFetcher?.sendCode(uid)
        }
    }

    override fun cancel() {
        //Code_fetcher?.cancel()
    }

}