package com.gacode.dog.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseMVPFragment<in V : BaseMVPView, P : BaseMVPPresenter<V>> : Fragment(), BaseMVPView {

    abstract var presenter: P

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            savedInstanceState?.let {
                Log.i("ANIL", it.toString())
            }
            return super.onCreateView(inflater, container, savedInstanceState)
        }
    }