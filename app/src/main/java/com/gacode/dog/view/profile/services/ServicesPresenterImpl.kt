package com.gacode.dog.view.Services.services

import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.util.Authentication

class ServicesPresenterImpl  : BaseMVPPresenterImpl<ServicesContract.ServicesView>(),
    ServicesContract.ServicesPresenter {

    override fun signOut(ServicesActivity: ServicesActivity) {

        ServicesActivity.context?.let { Authentication.delete(it) }
        view?.let { view -> call(view, view::onLogout) }
    }
}


