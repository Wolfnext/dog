package com.gacode.dog.view.splash

import android.content.Intent
import android.os.Bundle
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPActivity
import com.gacode.dog.view.auth.login.LoginActivity
import com.gacode.dog.view.home.HomeActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity: BaseMVPActivity<SplashContract.SplashView,
        SplashContract.SplashPresenter>(), SplashContract.SplashView {

    override var presenter: SplashContract.SplashPresenter = SplashPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        presenter.isAuthenticated()
    }

    override fun onSuccess() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onMessage(e: String) {
        txtMessage.text = e
    }

    override fun onLogin() {
        showLogin()
    }

    private fun showLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onError(e: Throwable) {
        Snackbar.make(container, e.message.toString(), Snackbar.LENGTH_LONG).show()
        showLogin()
    }
}