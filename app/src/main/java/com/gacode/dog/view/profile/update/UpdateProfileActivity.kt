package com.gacode.dog.view.profile.update

import android.os.Bundle
import android.util.Log
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPActivity
import com.gacode.dog.model.Profile
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_dogs.*
import kotlinx.android.synthetic.main.activity_edit_service.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_update_profile.*


class UpdateProfileActivity() : BaseMVPActivity<UpdateProfileContract.UpdateProfileView, UpdateProfileContract.UpdateProfilePresenter>(),
    UpdateProfileContract.UpdateProfileView {

    override var presenter: UpdateProfileContract.UpdateProfilePresenter = UpdateProfilePresenterImpl()
    private var profileData : Profile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        this.profileData =  intent.getSerializableExtra("profile") as Profile
        Log.d("profileData",this.profileData.toString())

        updateUI();
        initGooglePlaces(this.profileData!!)


        btn_update.setOnClickListener { view ->
            update()
            presenter.updateProfile(this, this.profileData!!)

        }

    }

     fun initGooglePlaces(profile : Profile){
        Places.initialize(applicationContext, "AIzaSyCpXBpc_BM-MK7GB2CnOzZdL14okBztKy0")
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG))

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener  {
            override fun onPlaceSelected(place: Place) {
                // TODO: Get info about the selected place.
                updateLocation(place)
                Log.i("maps", "Place: ${place.name},${place.latLng} ")
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                Log.i("maps", "An error occurred: $status")
            }
        })
    }

    fun updateLocation(place : Place){
        this.profileData?.lat  = place.latLng.latitude.toDouble()
        this.profileData?.lon = place.latLng.longitude.toDouble()
        this.profileData?.city = place.name
    }

    fun update() {
        this.profileData!!.firstname = editProfile_firstname.text.toString()
       this.profileData!!.surname = editProfile_surname.text.toString()
       this.profileData!!.phone = editProfile_phone.text.toString()
       this.profileData!!.desc = editProfile_desc.text.toString()

    }

    fun updateUI(){
        if(this.profileData!!.firstname != null)editProfile_firstname.setText(this.profileData!!.firstname.toString())
        if(this.profileData!!.surname != null)editProfile_surname.setText(this.profileData!!.surname.toString())
        if(this.profileData!!.phone != null)editProfile_phone.setText(this.profileData!!.phone.toString())
        if(this.profileData!!.desc != null)editProfile_desc.setText(this.profileData!!.desc.toString())
    }

    override fun onSuccess(profile : Profile) {
        finish();
    }

    override fun onFailed(e: String) {
        Snackbar.make(containerDogs, e , Snackbar.LENGTH_LONG).show()
    }

    override fun onError(e: Throwable) {

    }

    override fun onLogout() {
        TODO("Not yet implemented")
    }
}