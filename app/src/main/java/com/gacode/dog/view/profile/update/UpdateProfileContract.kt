package com.gacode.dog.view.profile.update

import android.content.Context
import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView
import com.gacode.dog.model.Dog

object UpdateProfileContract  {
    interface UpdateProfileView : BaseMVPView {
        fun onSuccess(dog : Dog)
        fun onFailed(e: String)
        fun onError(e: Throwable)
        fun onLogout()
    }

    interface UpdateProfilePresenter : BaseMVPPresenter<UpdateProfileView> {
        fun signOut()
        fun cancel()
    }
}