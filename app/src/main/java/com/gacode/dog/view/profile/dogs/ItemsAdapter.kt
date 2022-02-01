package com.gacode.dog.view.profile.dogs

import android.content.Intent
import android.view.*
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gacode.dog.R
import com.gacode.dog.model.Dog
import com.gacode.dog.view.profile.dogs.editDog.EditDogActivity
import java.util.ArrayList


class ItemsAdapter(private val mList: ArrayList<Dog>, private val dogsActivity: DogsActivity) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>()  {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_dogs, parent, false)

        return ViewHolder(view, dogsActivity, mList)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]


        // sets the text to the textview from our itemHolder class
        holder.dogName.text = ItemsViewModel.name
        holder.dogDesc.text = ItemsViewModel.desc


        //holder.textTime.text

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }



    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View, private val dogsActivity: DogsActivity, private var mList: ArrayList<Dog>) : RecyclerView.ViewHolder(ItemView), View.OnLongClickListener, View.OnCreateContextMenuListener, PopupMenu.OnMenuItemClickListener {
        var dogName: TextView = itemView.findViewById(R.id.text_firstname)
        var dogDesc: TextView = itemView.findViewById(R.id.text_dogDesc)
        var presenter: DogsContract.DogsPresenter = DogsPresenterImpl()




        init {
            itemView.setOnLongClickListener(this)
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu, view: View,
                                         menuInfo: ContextMenu.ContextMenuInfo?) {

        }



        override fun onLongClick(v: View?): Boolean {

            if (v != null) {
                //Toast.makeText(v.context, "long click", Toast.LENGTH_SHORT).show()
                showPopupMenu(v);
            }
                //Log.d("event","click")

            return true

        }

        private fun showPopupMenu(view: View) {
            val popupMenu = PopupMenu(view.context, view)
            popupMenu.inflate(R.menu.popup_menu)
            popupMenu.setOnMenuItemClickListener(this)
            popupMenu.show()
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            val ItemsViewModel = mList[position]
            return when (item!!.itemId) {
                R.id.popup_item_1 -> {

                    val intent = Intent(dogsActivity.activity, EditDogActivity::class.java)
                    intent.putExtra("dog",ItemsViewModel)
                    dogsActivity.startActivity(intent)
                    true
                }
                R.id.popup_item_2 -> {
                    ItemsViewModel.id?.let {
                        presenter.deleteDog(dogsActivity.requireContext(),
                            it
                        )
                    }
                    true
                }
                else -> false
            }
        }
    }
}