package com.gacode.dog.view.profile.update

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPActivity
import com.gacode.dog.model.Dog

import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class UpdateProfileActivity() : BaseMVPActivity<UpdateProfileContract.UpdateProfileView, UpdateProfileContract.UpdateProfilePresenter>(),
    UpdateProfileContract.UpdateProfileView {

    override var presenter: UpdateProfileContract.UpdateProfilePresenter = UpdateProfilePresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)


        initGooglePlaces()
    }

    private fun initGooglePlaces(){
        Places.initialize(applicationContext, "AIzaSyCpXBpc_BM-MK7GB2CnOzZdL14okBztKy0")
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG))

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

    override fun onSuccess(dog: Dog) {
        TODO("Not yet implemented")
    }

    override fun onFailed(e: String) {
        TODO("Not yet implemented")
    }

    override fun onError(e: Throwable) {
        TODO("Not yet implemented")
    }

    override fun onLogout() {
        TODO("Not yet implemented")
    }
}