package com.gacode.dog.view.auth.register

import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView

object RegisterContract {
    interface RegisterView : BaseMVPView {
        fun onSuccess()
        fun onFailed(e: String)
        fun onError(e: Throwable)
        fun onLogin()
    }

    interface RegisterPresenter : BaseMVPPresenter<RegisterView> {
        fun register(email: String, password: String, type: Int)
        fun openActivation()
        fun openLogin()
        fun cancel()
    }
}