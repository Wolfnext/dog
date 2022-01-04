package com.gacode.dog.base

import com.gacode.dog.view.profile.ProfileContract

interface BaseMVPPresenter<in V : BaseMVPView> {
    fun attachView(view: V)
    fun detachView()
}