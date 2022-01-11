package com.gacode.dog.view.search.filter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPActivity
import com.gacode.dog.view.search.SearchActivity
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import kotlinx.android.synthetic.main.activity_filter.*


class FilterActivity() : BaseMVPActivity<FilterContract.FilterView, FilterContract.FilterPresenter>(),
    FilterContract.FilterView{

    override var presenter: FilterContract.FilterPresenter = FilterPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        date.setOnClickListener { view ->
            DatePickerFragment(date).show(supportFragmentManager, "datePicker")
        }

        time.setOnClickListener { view ->
            TimePickerFragment(time).show(supportFragmentManager, "timePicker")
        }

        btn_showResult.setOnClickListener { view ->
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }


        initGooglePlaces()
    }

    private fun initGooglePlaces(){
        Places.initialize(applicationContext, "AIzaSyCpXBpc_BM-MK7GB2CnOzZdL14okBztKy0")
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG))

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                // TODO: Get info about the selected place.
                Log.i("maps", "Place: ${place.name},${place.latLng} ")
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                Log.i("maps", "An error occurred: $status")
            }
        })
    }


    override fun onLogout() {

    }

}