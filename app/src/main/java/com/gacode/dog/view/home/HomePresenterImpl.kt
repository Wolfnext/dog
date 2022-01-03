package com.gacode.dog.view.home

import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.util.Authentication

class HomePresenterImpl : BaseMVPPresenterImpl<HomeContract.HomeView>(),
    HomeContract.HomePresenter {

    override fun signOut() {
        Authentication.delete(getContext())

        //view?.let { view -> call(view, view::onLogout)}
    }
}