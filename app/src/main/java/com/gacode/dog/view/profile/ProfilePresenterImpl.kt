package com.gacode.dog.view.profile

import android.util.Log
import com.gacode.dog.base.BaseMVPPresenterImpl

class ProfilePresenterImpl : BaseMVPPresenterImpl<ProfileContract.ProfileView>(),
    ProfileContract.ProfilePresenter {

    override fun signOut() {
        Log.d("log","signOUTPROF")
        //Authentication.delete(activity)

        view?.let { view -> call(view, view::onLogout) }
    }
}