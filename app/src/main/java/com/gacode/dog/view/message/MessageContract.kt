package com.gacode.dog.view.message

import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView


object MessageContract {
    interface MessageView : BaseMVPView {
        fun onLogout()
    }
    interface MessagePresenter : BaseMVPPresenter<MessageContract.MessageView> {}
}