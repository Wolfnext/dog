package com.gacode.dog.view.search.filter

import android.content.Intent
import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPActivity
import com.gacode.dog.model.Search
import com.gacode.dog.view.search.SearchActivity
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import kotlinx.android.synthetic.main.activity_filter.*
import java.util.*


class FilterActivity() : BaseMVPActivity<FilterContract.FilterView, FilterContract.FilterPresenter>(),
    FilterContract.FilterView{

    override var presenter: FilterContract.FilterPresenter = FilterPresenterImpl()
    private var lat : Double = 0.0
    private var lon : Double = 0.0
    private var ONE_MINUTE_IN_MILLIS : Long  = 60000;//millisecs

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
            val outputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
            val intent = Intent(this, SearchActivity::class.java)
            if(date.text != null && time.text != null) {

                var datetime_start = date.text.toString() +" " + time.text.toString()

                val date1: Date = SimpleDateFormat("yyyy-MM-DD HH:mm").parse(datetime_start)

                var milisAfter : Float = date1.time+(range_duration.value * ONE_MINUTE_IN_MILLIS)
                var datetime_end = outputFormat.format(milisAfter);

                Log.d("times", outputFormat.format(date1).toString())
                Log.d("times",datetime_end.toString())

                var radius = range_slider.value

                var searchQuery : Search = Search(
                    type = "2",
                    datetime_start = outputFormat.format(date1).toString(),
                    datetime_end = datetime_end.toString(),
                    lat = this.lat,
                    lon = this.lon,
                    radius = radius.toString(),
                    price_start = range_price.valueFrom.toInt(),
                    price_end = range_price.valueTo.toInt(),
                    firstname = "",
                    desc = "",
                    price=0,
                    size_dog = 1,
                    distance = 2.0
                )

                intent.putExtra("search", searchQuery)
                intent.putExtra("date",date.text)
                intent.putExtra("time",time.text)
                startActivity(intent)
            }
        }

        initGooglePlaces()
    }

    fun updateGEO(place : Place){
        this.lat = place.latLng.latitude
        this.lon = place.latLng.longitude
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

                Log.i("maps", "Place: ${place.name},${place.latLng} ")
                updateGEO(place)
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