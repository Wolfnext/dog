package com.gacode.dog.view.profile.services

import android.content.Intent
import android.view.*
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gacode.dog.R
import com.gacode.dog.model.Service


import com.gacode.dog.view.profile.services.editService.EditServiceActivity
import java.util.ArrayList


class ItemsAdapter(private val mList: ArrayList<Service>, private val serviceActivity: ServicesActivity) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>()  {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_service, parent, false)

        return ViewHolder(view, serviceActivity, mList)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        when(ItemsViewModel.type) {
            "WK" -> holder.serviceType.text = "Wyprowadzanie"
            "DC" -> holder.serviceType.text = "Opieka"
        }


        val binaryArrayDaysOfWeek : CharArray = Integer.toBinaryString(ItemsViewModel.daysOfWeek).toCharArray()
        val binaryArrayTime : CharArray = Integer.toBinaryString(ItemsViewModel.time).toCharArray()
        var textBuilderDaysOfWeek : String = "";
        var textBuilderTime : String = "";

        for (index in binaryArrayDaysOfWeek.indices) {
            if(binaryArrayDaysOfWeek[index] == '1' && index == 0) textBuilderDaysOfWeek += "Poniedziałek, "
            if(binaryArrayDaysOfWeek[index] == '1' && index == 1) textBuilderDaysOfWeek += "Wtorek, "
            if(binaryArrayDaysOfWeek[index] == '1' && index == 2) textBuilderDaysOfWeek += "Sroda, "
            if(binaryArrayDaysOfWeek[index] == '1' && index == 3) textBuilderDaysOfWeek += "Czwartek, "
            if(binaryArrayDaysOfWeek[index] == '1' && index == 4) textBuilderDaysOfWeek += "Piątek, "
            if(binaryArrayDaysOfWeek[index] == '1' && index == 5) textBuilderDaysOfWeek += "Sobota, "
            if(binaryArrayDaysOfWeek[index] == '1' && index == 6) textBuilderDaysOfWeek += "Niedziela"
        }

        holder.serviceDaysOfWeek.text = textBuilderDaysOfWeek


        for (index in binaryArrayTime.indices) {
            if (binaryArrayTime[index] == '1' && index == 0) textBuilderTime += "Rano, "
            if (binaryArrayTime[index] == '1' && index == 1) textBuilderTime += "Popołudniu, "
            if (binaryArrayTime[index] == '1' && index == 2) textBuilderTime += "Wieczór, "
        }

        holder.serviceTime.text = textBuilderTime



        when(ItemsViewModel.active) {
             "0" -> holder.serviceActive.text = "Nieaktywne"
             "1" -> holder.serviceActive.text = "Aktywne"
        }



        holder.servicePrice.text = ItemsViewModel.price.toString() + "PLN"


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }



    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View, private val serviceActivity: ServicesActivity, private var mList: ArrayList<Service>) : RecyclerView.ViewHolder(ItemView), View.OnLongClickListener, View.OnCreateContextMenuListener, PopupMenu.OnMenuItemClickListener {
        var serviceType: TextView = itemView.findViewById(R.id.text_firstname)
        var serviceDaysOfWeek: TextView = itemView.findViewById(R.id.text_serviceDaysOfWeek)
        var serviceTime: TextView = itemView.findViewById(R.id.text_serviceTime)
        var serviceActive: TextView = itemView.findViewById(R.id.text_serviceActive)
        var servicePrice: TextView = itemView.findViewById(R.id.text_servicePrice)

        var presenter: ServicesContract.ServicesPresenter = ServicesPresenterImpl()

        init {
            itemView.setOnLongClickListener(this)
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu, view: View,
                                         menuInfo: ContextMenu.ContextMenuInfo?) {

        }



        override fun onLongClick(v: View?): Boolean {
            if (v != null) {
                showPopupMenu(v);
            }

            return true

        }

        private fun showPopupMenu(view: View) {
            val popupMenu = PopupMenu(view.context, view)
            popupMenu.inflate(R.menu.edit_menu)
            popupMenu.setOnMenuItemClickListener(this)
            popupMenu.show()
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            val ItemsViewModel = mList[position]
            return when (item!!.itemId) {
                R.id.popup_item_1 -> {
                    val intent = Intent(serviceActivity.activity, EditServiceActivity::class.java)
                    intent.putExtra("service",ItemsViewModel)
                    serviceActivity.startActivity(intent)
                    true

                }
                else -> false
            }
        }
    }
}