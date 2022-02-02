package com.gacode.dog.view.search

import android.app.ActionBar
import android.app.Dialog
import android.content.ClipData.Item
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gacode.dog.R
import com.gacode.dog.model.Dog
import com.gacode.dog.model.Dog_fetcher
import kotlinx.android.synthetic.main.activity_search.*
import com.gacode.dog.view.home.HomeActivity


class ItemsAdapter(private val mList: List<ItemsViewModel>, private val searchActivity: SearchActivity) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_results, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        var DogsFetcher: Dog_fetcher.DogFetcherImpl?= null
        // sets the image to the imageview from our itemHolder class
        //holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textFirstname.text = ItemsViewModel.firstname
        holder.textDesc.text = ItemsViewModel.desc
        holder.textDistance.text = ( Math.floor(ItemsViewModel.distance * 100) / 100).toString() + " km od Ciebie"
        holder.textPrice.text = ItemsViewModel.price.toString() + " PLN"

        holder.btnBookin.setOnClickListener{view ->
            var arraySpinner = ArrayList<Dog>()
            DogsFetcher = Dog_fetcher.DogFetcherImpl(searchActivity, object : Dog_fetcher.Listener {
                override fun onSuccess() {
                    TODO("Not yet implemented")
                }

                override fun onSuccessDog(dog: Dog?) {
                    TODO("Not yet implemented")
                }

                override fun onSuccessArray(dog: ArrayList<Dog>?) {
                    if (dog != null) {
                        arraySpinner = dog
                    }
                }

                override fun onError(throwable: Throwable) {

                }
            })

            DogsFetcher?.getDog(searchActivity)


            /*val s: Spinner = findViewById<View>(R.id.editSpinner_active) as Spinner
            val adapter: ArrayAdapter<Dog> = ArrayAdapter<Dog>(
                searchActivity,
                android.R.layout.simple_spinner_dropdown_item,
                arraySpinner
            )
            s.setAdapter(adapter)

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            s.setAdapter(adapter)*/

            val fbDialogue = Dialog(searchActivity, android.R.style.Theme_Black_NoTitleBar)
            fbDialogue.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.argb(100, 0, 0, 0)))
            fbDialogue.getWindow()?.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT)
            fbDialogue.getWindow()?.setGravity(Gravity.CENTER);
            fbDialogue.setContentView(R.layout.fragment_choice)
            fbDialogue.setCancelable(true)
            fbDialogue.show()
        }
    }




    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textPrice: TextView = itemView.findViewById(R.id.text_price)
        val textFirstname: TextView = itemView.findViewById(R.id.text_firstname)
        val textDesc: TextView = itemView.findViewById(R.id.text_desc)
        val textDistance: TextView = itemView.findViewById(R.id.text_distance)
        val btnBookin: Button = itemView.findViewById(R.id.btn_bookin)
    }
}