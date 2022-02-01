package com.gacode.dog.view.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPFragment
import com.gacode.dog.model.Profile
import com.gacode.dog.util.JWTUtil

import com.gacode.dog.view.auth.login.LoginActivity

import com.gacode.dog.view.profile.update.UpdateProfileActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.progressBar
import kotlinx.android.synthetic.main.activity_login.txtEmail
import kotlinx.android.synthetic.main.activity_login.txtPassword
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.view.*
import kotlinx.android.synthetic.main.activity_register.*


class ProfileActivity() : BaseMVPFragment<ProfileContract.ProfileView,ProfileContract.ProfilePresenter>(), ProfileContract.ProfileView
     {
         override var presenter: ProfileContract.ProfilePresenter = ProfilePresenterImpl()
         private var type : String? = null;
         private var profileData : Profile? = null;

         override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                   savedInstanceState: Bundle?): View? {

             val view: View = inflater!!.inflate(R.layout.activity_profile, container, false)

             presenter.attachView(this)

             val uid = this.context?.let { JWTUtil.getUID(it) }
             this.context?.let { presenter.getProfile(it) }

             return view
         }

         override fun onStart() {
             super.onStart()
             this.context?.let { presenter.getProfile(it) }
         }

         override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
             super.onViewCreated(view, savedInstanceState)

             type = this.context?.let { JWTUtil.getType(it) }

             initialTabLayout()

             view.btnUpdateProfile.setOnClickListener { view ->
                 this.context?.let { presenter.getProfile(it) }
                 activity?.let{
                     val intent = Intent (it, UpdateProfileActivity::class.java)
                     intent.putExtra("profile", this.profileData)
                     it.startActivity(intent)
                 }
             }

             view.btnLogout.setOnClickListener { view ->
                 presenter.signOut(this)
             }
         }

         private fun updateUI(profile :Profile){
             this.profileData = profile
             text_name.text = profile.firstname + " "+ profile.surname+ " (" + profile.city + ") "
             when (type){
                 "1"-> text_type.text = "Właściciel psa"
                 "2"-> text_type.text = "Wyprowadzacz psów"
             }
         }

         private fun initialTabLayout(){
             val viewPager = viewPagerProfile
             val tabLayout = tabLayout
             val tabsArray = type?.let { tabsByTypeAccount(it) }

             if (tabsArray != null) {
                 val adapter = type?.let {
                     ViewPagerAdapter(this.parentFragmentManager, lifecycle, tabsArray.size,
                         it
                     )
                 }
                 viewPager.adapter = adapter
             }


             TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                 tab.text = tabsArray?.get(position)
             }.attach()
         }

         private fun tabsByTypeAccount(type : String) : MutableList<String>{
             val tabsArray : MutableList<String> = mutableListOf()
            when(type) {
                "1" -> {
                    tabsArray.add("Moje psy")
                    tabsArray.add("Historia rezerwacji")
                }
                "2" -> {
                    tabsArray.add("Moje usługi")
                    tabsArray.add("Historia rezerwacji")
                }
            }

             return tabsArray
         }


         override fun onLogout() {
             activity?.let{
                 val intent = Intent (it, LoginActivity::class.java)
                 it.startActivity(intent)
             }

         }

         override fun onSuccess(profile : Profile) {
            updateUI(profile)
         }

         private fun loading(value: Boolean){
             if(value) progressBar.visibility = View.VISIBLE
             else progressBar.visibility = View.GONE

         }

         override fun onFailed(e: String) {
             loading(false)
             Snackbar.make(containerProfile, e, Snackbar.LENGTH_LONG).show()
         }

         override fun onError(e: Throwable) {
             loading(false)
             Snackbar.make(containerProfile, e.message.toString(), Snackbar.LENGTH_LONG).show()
         }


     }