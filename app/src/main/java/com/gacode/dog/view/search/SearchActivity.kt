package com.gacode.dog.view.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPActivity
import com.gacode.dog.model.Dog
import com.gacode.dog.model.Search
import com.gacode.dog.view.calendar.CalendarActivity
import com.gacode.dog.view.home.HomeActivity
import com.gacode.dog.view.search.filter.FilterActivity
import com.gacode.dog.view.search.filter.FilterContract
import com.gacode.dog.view.search.filter.FilterPresenterImpl
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_choice.*

import kotlin.collections.ArrayList

class SearchActivity : BaseMVPActivity<SearchContract.SearchView, SearchContract.SearchPresenter>(),
SearchContract.SearchView {

    override var presenter: SearchContract.SearchPresenter = SearchPresenterImpl()
    private var searchQuery : Search? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        btn_filter.setOnClickListener { view ->
            val intent = Intent(this, FilterActivity::class.java)
            //if(searchQuery != null)intent.putExtra("filter")
            startActivity(intent)
            }

        btn_home.setOnClickListener { view ->
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

       if(intent.getSerializableExtra("search") != null)searchQuery = intent.getSerializableExtra("search") as Search

        if(searchQuery != null) {
            Log.d("Search", searchQuery.toString())
            presenter.getSearch(this, searchQuery!!)
        }

    }

    fun updateUI(search: ArrayList<Search>){
        val recyclerview = recycler_results

        recyclerview.layoutManager = LinearLayoutManager(this)


        val data = ArrayList<ItemsViewModel>()



        for (i in search) {
            data.add(ItemsViewModel(i.price,i.firstname, i.distance, i.desc))
        }


        val adapter = ItemsAdapter(data,this)


        recyclerview.adapter = adapter
    }

    override fun onLogout() {
        TODO("Not yet implemented")
    }



    override fun onSuccessArray(search: ArrayList<Search>) {
        TODO("Not yet implemented")
    }

    override fun onSuccess(search: ArrayList<Search>) {
       updateUI(search)
    }

    override fun onFailed(e: String) {
        TODO("Not yet implemented")
    }

    override fun onError(e: Throwable) {
        TODO("Not yet implemented")
    }
}