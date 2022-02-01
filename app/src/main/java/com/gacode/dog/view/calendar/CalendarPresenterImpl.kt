package com.gacode.dog.view.calendar

import android.content.Context
import android.util.Log
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPPresenterImpl
import com.gacode.dog.model.*
import com.gacode.dog.view.profile.ProfileContract
import com.gacode.dog.view.profile.dogs.DogsActivity

class CalendarPresenterImpl  : BaseMVPPresenterImpl<CalendarContract.CalendarView>(),
    CalendarContract.CalendarPresenter
{
private var BookingsFetcher: Booking_fetcher.BookingFetcherImpl?= null

override fun getBookings(context : Context) {

    BookingsFetcher = Booking_fetcher.BookingFetcherImpl(context, object : Booking_fetcher.Listener {
        override fun onSuccess() {
            TODO("Not yet implemented")
        }

        override fun onSuccessBooking(Booking: Booking?) {
            TODO("Not yet implemented")
        }

        override fun onSuccessArray(Booking: ArrayList<Booking>?) {
            if(Booking == null) {
                view?.let { view -> call(view,
                    context.getString(R.string.getBooking_error),
                    view::onFailed)
                }
            } else {
                view?.let { view -> call(view, Booking, view::onSuccess)}
            }
        }

        override fun onError(throwable: Throwable) {

            view?.let { view -> call(view, throwable, view::onError) }
        }
    })

    BookingsFetcher?.getBooking(context)
}

override fun cancelBooking(context: Context, id: Int, cancelReason: Cancel_Reason) {
    BookingsFetcher = Booking_fetcher.BookingFetcherImpl(context, object : Booking_fetcher.Listener {

        override fun onSuccess() {
            Log.d("response","cancelBooking")
           // view?.let { view -> call(view, view::onSuccess) }
        }

        override fun onSuccessBooking(Booking: Booking?) {
            TODO("Not yet implemented")
        }

        override fun onSuccessArray(Booking: ArrayList<Booking>?) {
            TODO("Not yet implemented")
        }


        override fun onError(throwable: Throwable) {
            view?.let { view -> call(view, throwable, view::onError) }
        }
    })

    BookingsFetcher?.cancelBooking(context, id, cancelReason)
}

override fun cancel() {
    BookingsFetcher?.cancel()
}


}