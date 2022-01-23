package com.gacode.dog.view.profile.dogs

import android.content.Context
import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView
import com.gacode.dog.model.Dog
import com.gacode.dog.view.profile.dogs.DogsActivity


object DogsContract {
    interface DogsView : BaseMVPView {

        fun onSuccess(dog : ArrayList<Dog>)
        fun onSuccess()
        fun onFailed(e: String)
        fun onError(e: Throwable)
        fun onLogout()

    }

    interface DogsPresenter : BaseMVPPresenter<DogsView> {
        fun signOut(DogsActivity: DogsActivity)
        fun getDogs(context: Context)
        fun deleteDog(context: Context, id:Int)
        fun cancel()
    }
}