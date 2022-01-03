package com.gacode.dog.view.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MessageActivity.newInstance] factory method to
 * create an instance of this fragment.
 */
class MessageActivity(override var presenter: MessageContract.MessagePresenter) : BaseMVPFragment<MessageContract.MessageView,MessageContract.MessagePresenter>(),MessageContract.MessageView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_message, container, false)
    }



    override fun onLogout() {
        TODO("Not yet implemented")
    }
}