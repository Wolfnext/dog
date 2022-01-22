package com.gacode.dog.view.home

import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPActivity
import com.gacode.dog.util.JWTUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes


class HomeActivity : BaseMVPActivity<HomeContract.HomeView, HomeContract.HomePresenter>(),
    HomeContract.HomeView {

    override var presenter: HomeContract.HomePresenter = HomePresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navController = findNavController(R.id.nav_fragment)
        bottomNavigationView.setupWithNavController(navController)


        val email = JWTUtil.getEmail(this)
        val type = JWTUtil.getType(this)
        if (email != null) {
            Log.d("email",email )
            if (type != null) {
                Log.d("type",type )
            }
        };

    }


}