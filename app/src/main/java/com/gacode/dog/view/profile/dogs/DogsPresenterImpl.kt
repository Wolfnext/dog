package com.gacode.dog.view.profile.dogs

import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.util.Authentication


class DogsPresenterImpl : BaseMVPPresenterImpl<DogsContract.DogsView>(),
DogsContract.DogsPresenter {

    override fun signOut(DogsActivity: DogsActivity) {

      //  DogsActivity.context?.let { Authentication.delete(it) }
        //view?.let { view -> call(view, view::onLogout) }
    }
}

