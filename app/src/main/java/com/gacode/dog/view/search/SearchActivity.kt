package com.gacode.dog.view.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacode.dog.R
import kotlinx.android.synthetic.main.activity_search.*

import java.util.ArrayList

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        val recyclerview = recycler_results

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..10) {
            data.add(ItemsViewModel(20, "Adrian Wolf " + i))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = ItemsAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }
}