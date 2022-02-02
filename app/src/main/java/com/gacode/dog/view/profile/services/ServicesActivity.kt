package com.gacode.dog.view.profile.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPFragment
import com.gacode.dog.model.Dog
import com.gacode.dog.model.Service
import com.gacode.dog.view.auth.login.LoginActivity
import com.gacode.dog.view.profile.dogs.editDog.EditDogActivity

import kotlinx.android.synthetic.main.activity_dogs.*
import kotlinx.android.synthetic.main.activity_services.*

import kotlin.collections.ArrayList

class ServicesActivity() : BaseMVPFragment<ServicesContract.ServicesView, ServicesContract.ServicesPresenter>(), ServicesContract.ServicesView {
    override var presenter: ServicesContract.ServicesPresenter = ServicesPresenterImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater!!.inflate(R.layout.activity_services, container, false)

        presenter.attachView(this)

        return view
    }

    override fun onStart() {
        super.onStart()

        this.context?.let { presenter.getServices(it) }
    }

    fun updateUI(Service : ArrayList<Service>){
        Log.d("services",Service.toString())
        val recyclerview = recyclerViewServices

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this.context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Service>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in Service) {
            data.add(i)
        }


        // This will pass the ArrayList to our Adapter
        val adapter =  ItemsAdapter(data, this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }

    override fun onSuccess(services: ArrayList<Service>) {
        updateUI(services)
    }


    override fun onSuccess() {
        this.context?.let { presenter.getServices(it) }
    }


    override fun onFailed(e: String) {

    }

    override fun onError(e: Throwable) {

    }


    override fun onLogout() {
        activity?.let {
            val intent = Intent(it, LoginActivity::class.java)
            it.startActivity(intent)
        }
    }

}