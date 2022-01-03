package com.gacode.dog.view.home

import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView

object HomeContract {

    interface HomeView : BaseMVPView {
      //  fun onLogout()
    }

    interface HomePresenter : BaseMVPPresenter<HomeView> {
        fun signOut()
    }
}