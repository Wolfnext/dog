package com.gacode.dog.view.profile.services.editService

import android.content.Context
import android.util.Log
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.model.Service

import com.gacode.dog.model.Service_fetcher


class EditServicePresenterImpl : BaseMVPPresenterImpl<EditServiceContract.EditServiceView>(),
    EditServiceContract.EditServicePresenter {

    private var EditServiceFetcher: Service_fetcher.ServiceFetcherImpl? = null



    override fun updateService(context: Context, service: Service) {
        EditServiceFetcher =
            Service_fetcher.ServiceFetcherImpl(context, object : Service_fetcher.Listener {

                override fun onSuccessService(service: Service?) {
                    Log.d("response", service.toString())
                    if (service == null) {
                        view?.let { view ->
                            call(
                                view,
                                context.getString(R.string.getService_error),
                                view::onFailed
                            )
                        }
                    } else {
                        view?.let { view -> call(view, service, view::onSuccess) }
                    }
                }

                override fun onSuccessArray(Service: ArrayList<Service>?) {

                }

                override fun onError(throwable: Throwable) {

                    view?.let { view -> call(view, throwable, view::onError) }
                }
            })

        EditServiceFetcher?.updateService(context, service)
    }

    override fun cancel() {
        EditServiceFetcher?.cancel()
    }

    override fun signOut(EditServiceActivity: EditServiceActivity) {

    }
}