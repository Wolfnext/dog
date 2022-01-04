package com.gacode.dog.base

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import com.gacode.dog.view.profile.ProfileContract

abstract class BaseMVPFragment<in V : BaseMVPView, P : BaseMVPPresenter<V>>
: Fragment(), BaseMVPView {

    abstract var presenter: P

  /*  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this as V)
    }*/


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedInstanceState?.let {
            Log.d("ANIL", it.toString())
        }

        presenter.attachView(this as V)
        Log.d("context",this.toString())

        Log.d("context",view.toString())
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


}