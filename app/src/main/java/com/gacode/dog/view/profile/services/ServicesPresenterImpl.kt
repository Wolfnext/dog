package com.gacode.dog.view.profile.services

import android.content.Context
import android.util.Log
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.model.Dog
import com.gacode.dog.model.Dog_fetcher
import com.gacode.dog.model.Service
import com.gacode.dog.model.Service_fetcher
import com.gacode.dog.util.Authentication
import com.gacode.dog.view.profile.dogs.DogsActivity
import com.gacode.dog.view.profile.dogs.DogsContract

class ServicesPresenterImpl  : BaseMVPPresenterImpl<ServicesContract.ServicesView>(),
    ServicesContract.ServicesPresenter {

        private var ServicesFetcher: Service_fetcher.ServiceFetcherImpl?= null


        override fun getServices(context : Context) {

            ServicesFetcher = Service_fetcher.ServiceFetcherImpl(context, object : Service_fetcher.Listener {

                override fun onSuccessService(Service: Service?) {

                }

                override fun onSuccessArray(Service: ArrayList<Service>?) {
                    if(Service == null) {
                        view?.let { view -> call(view,
                            context.getString(R.string.getService_error),
                            view::onFailed)
                        }
                    } else {
                        view?.let { view -> call(view, Service, view::onSuccess)}
                    }
                }

                override fun onError(throwable: Throwable) {

                    view?.let { view -> call(view, throwable, view::onError) }
                }
            })

            ServicesFetcher?.getService(context)
        }


        override fun cancel() {
            ServicesFetcher?.cancel()
        }




    override fun signOut(ServicesActivity: ServicesActivity) {
        ServicesActivity.context?.let { Authentication.delete(it) }
        view?.let { view -> call(view, view::onLogout) }
    }
}


