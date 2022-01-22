package com.gacode.dog.view.profile

import android.content.Context
import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView
import com.gacode.dog.model.Profile
import com.gacode.dog.view.profile.dogs.DogsActivity

object  ProfileContract {

    interface ProfileView : BaseMVPView {
        fun onLogout()
        fun onSuccess(profile :Profile)
        fun onFailed(e: String)
        fun onError(e: Throwable)
    }

    interface ProfilePresenter : BaseMVPPresenter<ProfileView> {
        fun getProfile(context: Context)
        fun cancel()
        fun signOut(ProfileActivity: ProfileActivity)
    }
}