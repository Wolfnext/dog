package com.gacode.dog.view.profile.update

import android.content.Context
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.model.Dog_fetcher
import com.gacode.dog.model.Profile
import com.gacode.dog.model.Profile_fetcher
import com.gacode.dog.view.profile.update.UpdateProfileContract

class UpdateProfilePresenterImpl : BaseMVPPresenterImpl<UpdateProfileContract.UpdateProfileView>(),
    UpdateProfileContract.UpdateProfilePresenter {

    private var ProfileFetcher: Profile_fetcher.ProfileFetcherImpl? = null

    override fun updateProfile(context : Context, profile :Profile) {
        ProfileFetcher = Profile_fetcher.ProfileFetcherImpl(context, object : Profile_fetcher.Listener {
            override fun onSuccess(profile: Profile?) {
                if(profile == null) {
                    view?.let { view -> call(view,
                        context.getString(R.string.getProfile_error),
                        view::onFailed)
                    }
                } else {
                    view?.let { view -> call(view, profile, view::onSuccess)}
                }
            }
            override fun onError(throwable: Throwable) {

                view?.let { view -> call(view, throwable, view::onError) }
            }
        })

        ProfileFetcher?.updateProfile(context, profile)
    }

    override fun signOut() {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }

}