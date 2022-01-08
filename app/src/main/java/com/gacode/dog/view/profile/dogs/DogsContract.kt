package com.gacode.dog.view.profile.dogs

import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView


object DogsContract {
    interface DogsView : BaseMVPView {

        fun onLogout()
    }

    interface DogsPresenter : BaseMVPPresenter<DogsView> {
        fun signOut(DogsActivity: DogsActivity)
    }
}