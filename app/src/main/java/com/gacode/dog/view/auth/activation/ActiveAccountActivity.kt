package com.gacode.dog.view.auth.activation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPActivity
import com.gacode.dog.model.user
import com.gacode.dog.util.Authentication

import com.gacode.dog.view.home.HomeActivity
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_active_account.*
import kotlinx.android.synthetic.main.activity_active_account.containerActiveAccount
import kotlinx.android.synthetic.main.activity_active_account.progressBar
import kotlinx.android.synthetic.main.activity_login.*
import java.util.Objects.toString

class ActiveAccountActivity : BaseMVPActivity<ActiveAccountContract.ActiveAccountView, ActiveAccountContract.ActiveAccountPresenter>(),
    ActiveAccountContract.ActiveAccountView {

    override var presenter: ActiveAccountContract.ActiveAccountPresenter = ActiveAccountPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_account)

        val uid  = Authentication.getUser(this)?.uid
        val view: SmsConfirmationView = findViewById(R.id.sms_code_view)

        view.onChangeListener = SmsConfirmationView.OnChangeListener { code, isComplete ->
            if(isComplete) {
                loading(true)
                presenter.activeAccount(code, uid)
            }
        }

        btnSendCode.setOnClickListener {
                loading(true)
                presenter.sendCode(uid)
            }

    }


    override fun onSuccess() {
        loading(false)
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()

    }

    override fun onComplete(e: String) {
        loading(false)
        Snackbar.make(containerActiveAccount, e, Snackbar.LENGTH_LONG).show()

    }

    private fun loading(value: Boolean){
        if(value) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE

       /* if(!value){
            txtEmail.text?.clear()
            txtPassword.text?.clear()
        }*/

        /*  if(!value) txtEmail.visibility = View.VISIBLE
          else inputLayoutEmail.visibility = View.GONE

          if(!value) inputLayoutPassword.visibility = View.VISIBLE
          else inputLayoutPassword.visibility = View.GONE*/
    }

    override fun onFailed(e: String) {
        loading(false)
        Snackbar.make(containerActiveAccount, e, Snackbar.LENGTH_LONG).show()
    }

    override fun onError(e: Throwable) {
        loading(false)
        Snackbar.make(containerActiveAccount, e.message.toString(), Snackbar.LENGTH_LONG).show()
    }
}