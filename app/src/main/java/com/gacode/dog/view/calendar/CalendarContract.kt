package com.gacode.dog.view.calendar

import android.content.Context
import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView
import com.gacode.dog.model.Booking
import com.gacode.dog.model.Cancel_Reason
import com.gacode.dog.model.Dog

object CalendarContract {
    interface CalendarView : BaseMVPView {
        fun onSuccess(booking: ArrayList<Booking>)
        fun onSuccess()
        fun onFailed(e: String)
        fun onError(e: Throwable)
        fun onLogout()
    }
    interface CalendarPresenter : BaseMVPPresenter<CalendarContract.CalendarView> {
        fun getBookings(context: Context)
        fun cancelBooking(context: Context, id: Int, cancelReason: Cancel_Reason)
        fun cancel()
    }
}