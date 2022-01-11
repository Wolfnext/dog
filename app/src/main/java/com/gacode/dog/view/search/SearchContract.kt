package com.gacode.dog.view.search

import com.gacode.dog.base.BaseMVPPresenter
import com.gacode.dog.base.BaseMVPView

object SearchContract {
    interface SearchView : BaseMVPView {

    }
    interface SearchPresenter : BaseMVPPresenter<SearchView> {}
}