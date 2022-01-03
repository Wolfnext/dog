package com.gacode.dog.view.home

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : BaseMVPActivity<HomeContract.HomeView, HomeContract.HomePresenter>(),
    HomeContract.HomeView {

    override var presenter: HomeContract.HomePresenter = HomePresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navController = findNavController(R.id.nav_fragment)
        bottomNavigationView.setupWithNavController(navController)

       /* btnSignOut.setOnClickListener {
            btnSignOut.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE

            presenter.signOut()
        }*/
    }

    /*override fun onLogout() {
        Log.d("log","Homestartactivity")
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }*/
}