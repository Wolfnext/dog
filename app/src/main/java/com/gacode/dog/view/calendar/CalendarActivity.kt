package com.gacode.dog.view.calendar

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPFragment
import com.gacode.dog.model.Booking
import com.gacode.dog.util.JWTUtil
import com.gacode.dog.view.search.SearchActivity
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener
import com.github.sundeepk.compactcalendarview.domain.Event
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_calendar.*
import kotlinx.android.synthetic.main.activity_filter.*
import java.util.*
import kotlin.collections.ArrayList


class CalendarActivity() : BaseMVPFragment<CalendarContract.CalendarView,CalendarContract.CalendarPresenter>(), CalendarContract.CalendarView
   {
       private val dateFormatForMonth: SimpleDateFormat = SimpleDateFormat("MMM - yyyy", Locale.getDefault())
    override var presenter: CalendarContract.CalendarPresenter = CalendarPresenterImpl()
    private var type : String? = null
       private  val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.activity_calendar, container, false)
        presenter.attachView(this)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        this.type = this.context?.let { JWTUtil.getType(it) }
        when (this.type){
            "1" -> btn_search.visibility = Button.VISIBLE
            "2" -> btn_search.visibility = Button.INVISIBLE
        }

        btn_search.setOnClickListener { view ->
            activity?.let{
                val intent = Intent (it, SearchActivity::class.java)
                it.startActivity(intent)
            }
         }

        this.context?.let { presenter.getBookings(it) }

    }

       fun updateRecycler(booking: ArrayList<Booking>){
           val recyclerview = recyclerView

           recyclerview.adapter = null
           // this creates a vertical layout Manager
           recyclerview.layoutManager = LinearLayoutManager(this.context)

           // ArrayList of class ItemsViewModel
           var data : ArrayList<ItemsViewModel> = ArrayList<ItemsViewModel>()

           val outputFormat: DateFormat = SimpleDateFormat("HH:mm")

           when (this.type){
               "1" -> for (i in booking) {
                   data.add(ItemsViewModel(i.id,outputFormat.format(inputFormat.parse(i.time_start.toString())).toString(), outputFormat.format(inputFormat.parse(i.time_end.toString())).toString(),i.sitter_firstname,i.dog_name,i.sitterId, i.ownerId,i.status))
               }
               "2" -> for (i in booking) {
                   data.add(ItemsViewModel(i.id, outputFormat.format(inputFormat.parse(i.time_start.toString())).toString(), outputFormat.format(inputFormat.parse(i.time_end.toString())).toString(),i.owner_firstname,i.dog_name,i.sitterId,i.ownerId,i.status))
               }
           }

           // This will pass the ArrayList to our Adapter
           val adapter = ItemsAdapter(data, this, presenter)

           // Setting the Adapter with the recyclerview
           recyclerview.adapter = adapter


       }

       fun updateUI(booking: ArrayList<Booking>){

           //compactcalendar_view.setFirstDayOfWeek(Calendar.MONDAY)

           val polishZone : TimeZone = TimeZone.getTimeZone("Poland");
           val polishLocale : Locale = Locale("pl","PL")

           //compactcalendar_view.setLocale(polishZone, polishLocale);
           compactcalendar_view.setCurrentDate(Date())

           val df: SimpleDateFormat = SimpleDateFormat("LLLL YYYY", Locale("pl", "PL"))

           Log.d("date",dateFormatForMonth.format(compactcalendar_view.firstDayOfCurrentMonth).toString())
           text_month.text = df.format(compactcalendar_view.getFirstDayOfCurrentMonth())
           // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT
           compactcalendar_view.removeAllEvents()
           // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT

           for (i in booking) {
               if(i.status == "0") {
                   val ev1 = Event(Color.RED, inputFormat.parse(i.time_start.toString()).time, i )
                   compactcalendar_view.addEvent(ev1)
               }
               else  if(i.status == "1") {
                   val ev1 = Event(Color.GREEN, inputFormat.parse(i.time_start.toString()).time, i )
                   compactcalendar_view.addEvent(ev1)
               }


               }


           // Query for events on Sun, 07 Jun 2015 GMT.
           // Time is not relevant when querying for events, since events are returned by day.
           // So you can pass in any arbitary DateTime and you will receive all events for that day.

           // Query for events on Sun, 07 Jun 2015 GMT.
           // Time is not relevant when querying for events, since events are returned by day.
           // So you can pass in any arbitary DateTime and you will receive all events for that day.
           val events: List<Event> =
               compactcalendar_view.getEvents(Date().time) // can also take a Date object

           var listBookings : ArrayList<Booking> = ArrayList<Booking>()
           if(!events.isEmpty())for(i in events){
               listBookings.add(i.data as Booking)
           }
           updateRecycler(listBookings)

           // events has size 2 with the 2 events inserted previously

           // events has size 2 with the 2 events inserted previously
           Log.d(TAG, "Events: $events")



           // define a listener to receive callbacks when certain events happen.

           // define a listener to receive callbacks when certain events happen.
           compactcalendar_view.setListener(object : CompactCalendarViewListener {
               override fun onDayClick(dateClicked: Date) {
                   val events: List<Event> = compactcalendar_view.getEvents(dateClicked)
                   Log.d(TAG, "Day was clicked: $dateClicked with events $events")
                   var listBookings : ArrayList<Booking> = ArrayList<Booking>()
                   if(!events.isEmpty())for(i in events){
                       listBookings.add(i.data as Booking)
                   }
                   updateRecycler(listBookings)


               }

               override fun onMonthScroll(firstDayOfNewMonth: Date) {
                   text_month.text = df.format(compactcalendar_view.getFirstDayOfCurrentMonth())
                   Log.d(TAG, "Month was scrolled to: $firstDayOfNewMonth")
               }
           })


       }

       override fun onSuccess(booking: ArrayList<Booking>) {
           Log.d("bookings",booking.toString())
           updateUI(booking)

       }

        override fun onSuccess(e :String) {
           Snackbar.make(containerCalendar, R.string.confirmBooking, Snackbar.LENGTH_SHORT).show()
           this.context?.let { presenter.getBookings(it) }
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


