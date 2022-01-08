package com.gacode.dog.view.Services.services

import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView


object ServicesContract {

    interface ServicesView : BaseMVPView {
        fun onLogout()
    }

    interface ServicesPresenter : BaseMVPPresenter<ServicesView> {
        fun signOut(ServicesActivity: ServicesActivity)
    }
}
