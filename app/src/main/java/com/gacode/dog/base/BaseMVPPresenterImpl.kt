package com.gacode.dog.base

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.gacode.dog.view.profile.ProfileContract


open class BaseMVPPresenterImpl<V : BaseMVPView> : BaseMVPPresenter<V> {

     open var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    protected fun call(view: V, function: () -> Unit) {
        Log.d("context",view.toString())
        when (view) {
            is Fragment -> (view as Fragment).activity?.runOnUiThread { function() }
            is Activity -> (view as Activity).runOnUiThread { function() }
        }
    }

    protected fun <T> call(view: V, parameter: T, function: (parameter: T) -> Unit) {
        Log.d("context",view.toString())
        when (view) {
            is Fragment -> (view as Fragment).activity?.runOnUiThread { function(parameter) }
            is Activity -> (view as Activity).runOnUiThread { function(parameter) }
        }
    }

    protected fun getContext(): Context {
        Log.d("context",view.toString())
        return when (view) {
            is Fragment -> (view as FragmentActivity)
            is Activity -> (view as Activity)
            else -> throw Exception()
        }
    }
}