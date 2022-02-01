package com.gacode.dog.view.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gacode.dog.R
import com.gacode.dog.util.JWTUtil
import com.google.android.material.button.MaterialButton

class ItemsAdapter(private val mList: List<ItemsViewModel>, private val calendarActivity: CalendarActivity) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_booking, parent, false)

        return ViewHolder(view, calendarActivity)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        //holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class

        holder.textTime.text = ItemsViewModel.start_time + " - " + ItemsViewModel.end_time

        val type = this.calendarActivity.context?.let { JWTUtil.getType(it) }
        if(type == "1"){
            holder.textFirstname.text = "Wyprowadza "+ItemsViewModel.firstname
            holder.textDog.text = ItemsViewModel.dogText
            holder.imageconfirm.visibility = INVISIBLE
            holder.imagecancel.visibility = INVISIBLE
        }
        else {
            holder.textFirstname.text = "Właściciel: "+ItemsViewModel.firstname
            holder.textDog.text = "Spacer z " +ItemsViewModel.dogText
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View, val calendarActivity : CalendarActivity) : RecyclerView.ViewHolder(ItemView) {
        val textTime: TextView = itemView.findViewById(R.id.text_time)
        val textDog: TextView = itemView.findViewById(R.id.text_dogDesc)
        val textFirstname: TextView = itemView.findViewById(R.id.text_firstname)
        val imageconfirm: MaterialButton = itemView.findViewById(R.id.button_ConfirmBooking)
        val imagecancel: MaterialButton = itemView.findViewById(R.id.button_cancelBooking)
    }
}