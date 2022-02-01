package com.gacode.dog.view.profile.services.editService

import android.content.Context
import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView
import com.gacode.dog.model.Dog
import com.gacode.dog.model.Service


object EditServiceContract  {
    interface EditServiceView : BaseMVPView {
        fun onSuccess(service : Service)
        fun onFailed(e: String)
        fun onError(e: Throwable)
        fun onLogout()
    }

    interface EditServicePresenter : BaseMVPPresenter<EditServiceView> {
        fun signOut(EditServiceActivity: EditServiceActivity)
        fun updateService(context: Context, service : Service)
        fun cancel()
    }
}