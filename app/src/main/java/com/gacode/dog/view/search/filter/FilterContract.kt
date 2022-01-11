package com.gacode.dog.view.search.filter

import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView

object FilterContract {
    interface FilterView : BaseMVPView {
        fun onLogout()
    }
    interface FilterPresenter : BaseMVPPresenter<FilterView> {}
}