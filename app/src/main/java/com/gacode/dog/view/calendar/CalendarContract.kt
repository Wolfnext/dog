package com.gacode.dog.view.calendar

import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView

object CalendarContract {
    interface CalendarView : BaseMVPView {
        fun onLogout()
    }
    interface CalendarPresenter : BaseMVPPresenter<CalendarContract.CalendarView> {}
}