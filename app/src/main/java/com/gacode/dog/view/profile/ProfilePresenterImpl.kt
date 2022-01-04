package com.gacode.dog.view.profile

import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.util.Authentication


class ProfilePresenterImpl : BaseMVPPresenterImpl<ProfileContract.ProfileView>(),
    ProfileContract.ProfilePresenter {

     override fun signOut(profileActivity: ProfileActivity) {

         profileActivity.context?.let { Authentication.delete(it) }
        view?.let { view -> call(view, view::onLogout) }
    }



}