package com.gacode.dog.view.profile.dogs.editDog

import android.content.Context
import android.util.Log
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.model.Dog
import com.gacode.dog.model.Dog_fetcher
import com.gacode.dog.view.profile.dogs.editDog.EditDogActivity
import com.gacode.dog.view.profile.dogs.editDog.EditDogContract

class EditDogPresenterImpl : BaseMVPPresenterImpl<EditDogContract.EditDogView>(),
    EditDogContract.EditDogPresenter {

    private var EditDogFetcher: Dog_fetcher.DogFetcherImpl? = null

    override fun createDog(context: Context, dog: Dog) {
        EditDogFetcher = Dog_fetcher.DogFetcherImpl(context, object : Dog_fetcher.Listener {
            override fun onSuccess() {
                TODO("Not yet implemented")
            }

            override fun onSuccessDog(dog: Dog?) {
                if (dog == null) {
                    view?.let { view ->
                        call(
                            view,
                            context.getString(R.string.postDog_error),
                            view::onFailed
                        )
                    }
                } else {
                    view?.let { view -> call(view, dog, view::onSuccess) }
                }
            }

            override fun onSuccessArray(dog: ArrayList<Dog>?) {
                TODO("Not yet implemented")
            }

            override fun onError(throwable: Throwable) {

                view?.let { view -> call(view, throwable, view::onError) }
            }
        })

        EditDogFetcher?.createDog(context,dog )
    }


    override fun updateDog(context: Context, id: Int, dog: Dog) {
        EditDogFetcher = Dog_fetcher.DogFetcherImpl(context, object : Dog_fetcher.Listener {
            override fun onSuccess() {
                TODO("Not yet implemented")
            }

            override fun onSuccessDog(dog: Dog?) {
                Log.d("response",dog.toString())
                if (dog == null) {
                    view?.let { view ->
                        call(
                            view,
                            context.getString(R.string.getDog_error),
                            view::onFailed
                        )
                    }
                } else {
                    view?.let { view -> call(view, dog, view::onSuccess) }
                }
            }

            override fun onSuccessArray(dog: ArrayList<Dog>?) {
                TODO("Not yet implemented")
            }

            override fun onError(throwable: Throwable) {

                view?.let { view -> call(view, throwable, view::onError) }
            }
        })

        EditDogFetcher?.updateDog(context, id, dog)
    }

    override fun cancel() {
        EditDogFetcher?.cancel()
    }

    override fun signOut(EditDogActivity: EditDogActivity) {

    }
}