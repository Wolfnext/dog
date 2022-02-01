package com.gacode.dog.view.profile.services

import android.content.Context
import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView
import com.gacode.dog.model.Dog
import com.gacode.dog.model.Service


object ServicesContract {

    interface ServicesView : BaseMVPView {
        fun onSuccess(dog : ArrayList<Service>)
        fun onSuccess()
        fun onFailed(e: String)
        fun onError(e: Throwable)
        fun onLogout()
    }

    interface ServicesPresenter : BaseMVPPresenter<ServicesView> {
        fun signOut(ServicesActivity: ServicesActivity)
        fun getServices(context: Context)
        fun cancel()
    }
}
