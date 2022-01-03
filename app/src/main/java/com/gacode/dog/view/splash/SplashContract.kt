package com.gacode.dog.view.splash

import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView

object SplashContract {

    interface SplashView : BaseMVPView {
        fun onSuccess()
        fun onMessage(e: String)
        fun onLogin()
        fun onError(e: Throwable)
    }

    interface SplashPresenter : BaseMVPPresenter<SplashView> {
        fun isAuthenticated()
        fun cancel()
    }
}