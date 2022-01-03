package com.gacode.dog.view.profile

import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView

object  ProfileContract {

    interface ProfileView : BaseMVPView {
        fun onLogout()
    }

    interface ProfilePresenter : BaseMVPPresenter<ProfileView> {
        fun signOut()
    }
}