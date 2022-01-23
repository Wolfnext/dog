package com.gacode.dog.view.profile.dogs.editDog

import android.content.Context
import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView
import com.gacode.dog.model.Dog

    object EditDogContract  {
        interface EditDogView : BaseMVPView {
            fun onSuccess(dog : Dog)
            fun onFailed(e: String)
            fun onError(e: Throwable)
            fun onLogout()
        }

        interface EditDogPresenter : BaseMVPPresenter<EditDogView> {
            fun signOut(EditDogActivity: EditDogActivity)
            fun updateDog(context: Context, id: Int, dog : Dog)
            fun createDog(context: Context, dog:Dog)
            fun cancel()
        }
    }