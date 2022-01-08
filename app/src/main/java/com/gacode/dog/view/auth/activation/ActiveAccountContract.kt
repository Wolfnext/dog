package com.gacode.dog.view.auth.activation

import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView
import com.gacode.dog.model.auth.Code

object ActiveAccountContract {

    interface ActiveAccountView : BaseMVPView {
        fun onSuccess()
        fun onComplete(e: String)
        fun onFailed(e: String)
        fun onError(e: Throwable)
    }

    interface ActiveAccountPresenter : BaseMVPPresenter<ActiveAccountView> {
        fun sendCode(uid:String?)
        fun activeAccount(code: String, uid:String?)
        fun cancel()
    }
}