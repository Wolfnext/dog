package com.gacode.dog.view.profile.dogs

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPFragment
import com.gacode.dog.model.Dog
import com.gacode.dog.view.profile.dogs.editDog.EditDogActivity
import com.gacode.dog.view.profile.update.UpdateProfileActivity
import com.google.android.material.snackbar.Snackbar
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

        return view
    }

    override fun onStart() {
        super.onStart()
        this.context?.let { presenter.getDogs(it) }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_addDog.setOnClickListener { view ->
            activity?.let{
                val intent = Intent(it, EditDogActivity::class.java)
                intent.putExtra("dog", Dog(null,"","","","","","",""))
                it.startActivity(intent)
            }
        }

    }

    fun updateUI(dog : ArrayList<Dog>){

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
        val adapter =  ItemsAdapter(data, this, presenter)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }

    override fun onSuccess(dog: ArrayList<Dog>) {
       updateUI(dog)
    }

    override fun onSuccess() {
        Snackbar.make(containerDogs, R.string.deleteDog, Snackbar.LENGTH_LONG).show()
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