package com.gacode.dog.view.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPActivity
import com.gacode.dog.view.home.HomeActivity
import com.gacode.dog.view.search.filter.FilterActivity
import com.gacode.dog.view.search.filter.FilterContract
import com.gacode.dog.view.search.filter.FilterPresenterImpl
import kotlinx.android.synthetic.main.activity_search.*

import java.util.ArrayList

class SearchActivity : BaseMVPActivity<SearchContract.SearchView, SearchContract.SearchPresenter>(),
SearchContract.SearchView {

    override var presenter: SearchContract.SearchPresenter = SearchPresenterImpl()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        btn_filter.setOnClickListener { view ->
            val intent = Intent(this, FilterActivity::class.java)
            startActivity(intent)
            }



        val recyclerview = recycler_results


        recyclerview.layoutManager = LinearLayoutManager(this)


        val data = ArrayList<ItemsViewModel>()



        for (i in 1..10) {
            data.add(ItemsViewModel(20, "Adrian Wolf " + i))
        }


        val adapter = ItemsAdapter(data)


        recyclerview.adapter = adapter

    }
}