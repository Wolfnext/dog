package com.gacode.dog.view.profile

import android.content.Context
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.model.Profile
import com.gacode.dog.model.Profile_fetcher
import com.gacode.dog.util.Authentication


class ProfilePresenterImpl : BaseMVPPresenterImpl<ProfileContract.ProfileView>(),
    ProfileContract.ProfilePresenter {

    private var ProfileFetcher: Profile_fetcher.ProfileFetcherImpl?= null

    override fun getProfile(context : Context, uid : String?) {
        ProfileFetcher = Profile_fetcher.ProfileFetcherImpl(context, object : Profile_fetcher.Listener {
            override fun onSuccess(profile: Profile?) {
                if(profile == null) {
                    view?.let { view -> call(view,
                        context.getString(R.string.getProfile_error),
                        view::onFailed)
                    }
                } else {
                    view?.let { view -> call(view,profile, view::onSuccess)}
                }
            }
            override fun onError(throwable: Throwable) {

                view?.let { view -> call(view, throwable, view::onError) }
            }
        })
        ProfileFetcher?.getProfile(context, uid)
    }

    override fun cancel() {
        ProfileFetcher?.cancel()
    }

    override fun signOut(ProfileActivity: ProfileActivity) {
          ProfileActivity.context?.let { Authentication.delete(it) }
          view?.let { view -> call(view, view::onLogout) }
    }


}