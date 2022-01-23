package com.gacode.dog.view.profile.update

import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.model.Dog_fetcher
import com.gacode.dog.view.profile.update.UpdateProfileContract

class UpdateProfilePresenterImpl : BaseMVPPresenterImpl<UpdateProfileContract.UpdateProfileView>(),
    UpdateProfileContract.UpdateProfilePresenter {

    private var UpdateProfileFetcher: Dog_fetcher.DogFetcherImpl? = null
    override fun signOut() {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }

}