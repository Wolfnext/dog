package com.gacode.dog.view.calendar

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
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
import com.gacode.dog.util.JWTUtil
import com.gacode.dog.view.search.SearchActivity
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener
import com.github.sundeepk.compactcalendarview.domain.Event
import kotlinx.android.synthetic.main.activity_calendar.*
import kotlinx.android.synthetic.main.activity_calendar.view.*
import kotlinx.android.synthetic.main.activity_profile.view.*
import java.text.DateFormat
import java.time.LocalDateTime
import java.util.*


class CalendarActivity() : BaseMVPFragment<CalendarContract.CalendarView,CalendarContract.CalendarPresenter>(), CalendarContract.CalendarView
   {
       private val dateFormatForMonth: SimpleDateFormat = SimpleDateFormat("MMM - yyyy", Locale.getDefault())
    override var presenter: CalendarContract.CalendarPresenter = CalendarPresenterImpl()
    private var type : String? = null;


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.activity_calendar, container, false)
        presenter.attachView(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        type = this.context?.let { JWTUtil.getType(it) }
        when (type){
            "1" -> btn_search.visibility = Button.VISIBLE
            "2" -> btn_search.visibility = Button.INVISIBLE
        }

        btn_search.setOnClickListener { view ->
            activity?.let{
                val intent = Intent (it, SearchActivity::class.java)
                it.startActivity(intent)
            }
         }


        val recyclerview = recyclerView

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this.context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..5) {
            data.add(ItemsViewModel(R.drawable.dog_icon, "Roman S. " + i))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = ItemsAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter









        //compactcalendar_view.setFirstDayOfWeek(Calendar.MONDAY)

        val polishZone : TimeZone = TimeZone.getTimeZone("Poland");
        val polishLocale : Locale = Locale("pl","PL")

        //compactcalendar_view.setLocale(polishZone, polishLocale);
        compactcalendar_view.setCurrentDate(Date())


        val df: SimpleDateFormat = SimpleDateFormat("LLLL YYYY", Locale("pl", "PL"))

        Log.d("date",dateFormatForMonth.format(compactcalendar_view.firstDayOfCurrentMonth).toString())
        text_month.text = df.format(compactcalendar_view.getFirstDayOfCurrentMonth())
        // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT

        // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT
        val ev1 = Event(Color.GREEN, 	1641747636, "Some extra data that I want to store.")
        compactcalendar_view.addEvent(ev1)

        // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT

        // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT
        val ev2 = Event(Color.GREEN, 1641664777106)
        compactcalendar_view.addEvent(ev2)

        // Query for events on Sun, 07 Jun 2015 GMT.
        // Time is not relevant when querying for events, since events are returned by day.
        // So you can pass in any arbitary DateTime and you will receive all events for that day.

        // Query for events on Sun, 07 Jun 2015 GMT.
        // Time is not relevant when querying for events, since events are returned by day.
        // So you can pass in any arbitary DateTime and you will receive all events for that day.
        val events: List<Event> =
            compactcalendar_view.getEvents(1433701251000L) // can also take a Date object


        // events has size 2 with the 2 events inserted previously

        // events has size 2 with the 2 events inserted previously
        Log.d(TAG, "Events: $events")



        // define a listener to receive callbacks when certain events happen.

        // define a listener to receive callbacks when certain events happen.
        compactcalendar_view.setListener(object : CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
                val events: List<Event> = compactcalendar_view.getEvents(dateClicked)
                Log.d(TAG, "Day was clicked: $dateClicked with events $events")
                Log.d("date",compactcalendar_view.getFirstDayOfCurrentMonth().toString())
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date) {
                text_month.text = df.format(compactcalendar_view.getFirstDayOfCurrentMonth())
                Log.d(TAG, "Month was scrolled to: $firstDayOfNewMonth")
            }
        })
    }




    override fun onLogout() {
        TODO("Not yet implemented")
    }
}


