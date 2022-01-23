package com.gacode.dog.view.profile.dogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPFragment
import com.gacode.dog.model.Dog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_dogs.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.*
import java.util.ArrayList

class DogsActivity : BaseMVPFragment<DogsContract.DogsView, DogsContract.DogsPresenter>(), DogsContract.DogsView {
    override var presenter: DogsContract.DogsPresenter = DogsPresenterImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater!!.inflate(R.layout.activity_dogs, container, false)

        presenter.attachView(this)

        return view
    }

    override fun onStart() {
        super.onStart()
        this.context?.let { presenter.getDogs(it) }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun updateUI(dog : ArrayList<Dog>){

        Log.d("dogs",dog.toString())
        val recyclerview = recyclerviewDogs

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this.context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Dog>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in dog) {
            data.add(i)
        }

        // This will pass the ArrayList to our Adapter
        val adapter =  ItemsAdapter(data, this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }

    override fun onSuccess(dog: ArrayList<Dog>) {
       updateUI(dog)
    }

    override fun onSuccess() {
        this.context?.let { presenter.getDogs(it) }
    }

    override fun onFailed(e: String) {
        Snackbar.make(containerDogs, e , Snackbar.LENGTH_LONG).show()
    }

    override fun onError(e: Throwable) {
        Snackbar.make(containerDogs, e.message.toString(), Snackbar.LENGTH_LONG).show()
    }

    override fun onLogout() {

    }


}