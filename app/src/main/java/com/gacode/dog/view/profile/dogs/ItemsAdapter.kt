package com.gacode.dog.view.profile.dogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gacode.dog.R

class ItemsAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_dogs, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        //holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.dogName.text = ItemsViewModel.dogName
        holder.dogDesc.text = ItemsViewModel.dogDesc

        //holder.textTime.text

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var dogName: TextView = itemView.findViewById(R.id.text_nameDog)
        var dogDesc: TextView = itemView.findViewById(R.id.text_dogDesc)
    }
}