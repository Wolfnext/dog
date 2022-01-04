package com.gacode.dog.view.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPFragment
import com.gacode.dog.view.auth.login.LoginActivity
import kotlinx.android.synthetic.main.activity_profile.view.*


class ProfileActivity() : BaseMVPFragment<ProfileContract.ProfileView,ProfileContract.ProfilePresenter>(), ProfileContract.ProfileView
     {
         override var presenter: ProfileContract.ProfilePresenter = ProfilePresenterImpl()

         override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                   savedInstanceState: Bundle?): View? {


             val view: View = inflater!!.inflate(R.layout.activity_profile, container, false)

             presenter.attachView(this)

             Log.d("context",view.toString())
             // Return the fragment view/layout
             return view
         }

         override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
             super.onViewCreated(view, savedInstanceState)

             view.btn_logout.setOnClickListener { view ->
                 Log.d("btnSetup", "Selected")
                 presenter.signOut(this)
             }
         }


         override fun onLogout() {
             Log.d("log","startactivity")
             activity?.let{
                 val intent = Intent (it, LoginActivity::class.java)
                 it.startActivity(intent)
             }
         }

}