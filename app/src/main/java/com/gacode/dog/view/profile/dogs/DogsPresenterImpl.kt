package com.gacode.dog.view.profile.dogs

import android.content.Context
import android.util.Log
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.model.Dog
import com.gacode.dog.model.Dog_fetcher


class DogsPresenterImpl : BaseMVPPresenterImpl<DogsContract.DogsView>(),
DogsContract.DogsPresenter {

    private var DogsFetcher: Dog_fetcher.DogFetcherImpl?= null


    override fun getDogs(context : Context) {

        DogsFetcher = Dog_fetcher.DogFetcherImpl(context, object : Dog_fetcher.Listener {
            override fun onSuccess() {
                TODO("Not yet implemented")
            }

            override fun onSuccessDog(dog: Dog?) {
                TODO("Not yet implemented")
            }

            override fun onSuccessArray(dog: ArrayList<Dog>?) {
                if(dog == null) {
                    view?.let { view -> call(view,
                        context.getString(R.string.getDog_error),
                        view::onFailed)
                    }
                } else {
                    view?.let { view -> call(view, dog, view::onSuccess)}
                }
            }

            override fun onError(throwable: Throwable) {

                view?.let { view -> call(view, throwable, view::onError) }
            }
        })

        DogsFetcher?.getDog(context)
    }

    override fun deleteDog(context: Context, id: Int) {
        DogsFetcher = Dog_fetcher.DogFetcherImpl(context, object : Dog_fetcher.Listener {

            override fun onSuccess() {
                Log.d("view",view.toString())
                    view?.let { view -> call(view, view::onSuccess) }
            }

            override fun onSuccessDog(dog: Dog?) {
                TODO("Not yet implemented")
            }

            override fun onSuccessArray(dog: ArrayList<Dog>?) {
                TODO("Not yet implemented")
            }

            override fun onError(throwable: Throwable) {
                view?.let { view -> call(view, throwable, view::onError) }
            }
        })

        DogsFetcher?.deleteDog(context, id)
    }

    override fun cancel() {
        DogsFetcher?.cancel()
    }

    override fun signOut(DogsActivity: DogsActivity) {

    }
}

