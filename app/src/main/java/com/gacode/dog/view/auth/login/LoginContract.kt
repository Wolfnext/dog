package com.gacode.dog.view.auth.login

import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView

object LoginContract {

    interface LoginView : BaseMVPView {
        fun onSuccess()
        fun onFailed(e: String)
        fun onError(e: Throwable)
    }

    interface LoginPresenter : BaseMVPPresenter<LoginView> {
        fun login(email: String, password: String)
        fun cancel()
    }
}