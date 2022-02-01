package com.gacode.dog.view.profile.update

import android.content.Context
import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView
import com.gacode.dog.model.Dog
import com.gacode.dog.model.Profile

object UpdateProfileContract  {
    interface UpdateProfileView : BaseMVPView {
        fun onSuccess(profile : Profile)
        fun onFailed(e: String)
        fun onError(e: Throwable)
        fun onLogout()
    }

    interface UpdateProfilePresenter : BaseMVPPresenter<UpdateProfileView> {
        fun signOut()
        fun updateProfile(context : Context, profile: Profile)
        fun cancel()
    }
}