package com.gacode.dog.view.profile.dogs

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
import com.gacode.dog.view.auth.login.LoginActivity
import com.gacode.dog.view.profile.dogs.ItemsAdapter
import com.gacode.dog.view.profile.dogs.ItemsViewModel
import kotlinx.android.synthetic.main.activity_dogs.*
import java.util.ArrayList

class DogsActivity : BaseMVPFragment<DogsContract.DogsView, DogsContract.DogsPresenter>(), DogsContract.DogsView {
    override var presenter: DogsContract.DogsPresenter = DogsPresenterImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater!!.inflate(R.layout.activity_dogs, container, false)

        presenter.attachView(this)
        this.context?.let { presenter.getDogs(it) }


        return view
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
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in dog) {
            data.add(ItemsViewModel("tt",i.name,i.desc))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = ItemsAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }

    override fun onSuccess(dog: ArrayList<Dog>) {
       updateUI(dog)
    }

    override fun onFailed(e: String) {
        TODO("Not yet implemented")
    }

    override fun onError(e: Throwable) {
        TODO("Not yet implemented")
    }

    override fun onLogout() {

    }

}