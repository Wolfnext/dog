package com.gacode.dog.view.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPActivity
import com.gacode.dog.view.auth.activation.ActiveAccountActivity
import com.gacode.dog.view.auth.login.LoginActivity
import com.gacode.dog.view.home.HomeActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btnRegister
import kotlinx.android.synthetic.main.activity_login.progressBar
import kotlinx.android.synthetic.main.activity_login.txtEmail
import kotlinx.android.synthetic.main.activity_login.txtPassword
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseMVPActivity<RegisterContract.RegisterView, RegisterContract.RegisterPresenter>(),
    RegisterContract.RegisterView {

    override var presenter: RegisterContract.RegisterPresenter = RegisterPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

    btnRegister.setOnClickListener {
        if(isValid()){
            loading(true)
            presenter.register(txtEmail.text.toString(), txtPassword.text.toString(), typeChecked())
        }
    }

    btnLogin.setOnClickListener {
        presenter.openLogin()

    }
}

    private fun typeChecked() : Int {
       val type = radioType.checkedRadioButtonId
        when(type){
            R.id.owner_type -> return 1
            R.id.sitter_type -> return 2
        }
        return 1
    }

    private fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


    private fun isValid(): Boolean {
    if(!txtEmail?.text.toString().isValidEmail()) {
        Snackbar.make(containerRegister, R.string.error_invalid_email, Snackbar.LENGTH_LONG).show()
        return false
    }
    if(txtPassword?.text.toString().isNullOrEmpty()){
        Snackbar.make(containerRegister, R.string.error_invalid_password, Snackbar.LENGTH_LONG).show()
        return false
    }
    if(txtConfirmPassword?.text.toString().isNullOrEmpty() || txtPassword?.text.toString() != txtConfirmPassword?.text.toString()){
        Snackbar.make(containerRegister, R.string.error_incorrect_password, Snackbar.LENGTH_LONG).show()
        return false
    }
    return true
}

override fun onSuccess() {
    val intent = Intent(this, ActiveAccountActivity::class.java)
    startActivity(intent)
    finish()
}

private fun loading(value: Boolean){
    if(value) progressBar.visibility = View.VISIBLE
    else progressBar.visibility = View.GONE

    if(!value){
        txtEmail.text?.clear()
        txtPassword.text?.clear()
        txtConfirmPassword.text?.clear()
    }

    /*  if(!value) txtEmail.visibility = View.VISIBLE
      else inputLayoutEmail.visibility = View.GONE

      if(!value) inputLayoutPassword.visibility = View.VISIBLE
      else inputLayoutPassword.visibility = View.GONE*/
}

override fun onFailed(e: String) {
    loading(false)
    Snackbar.make(containerRegister, e, Snackbar.LENGTH_LONG).show()
}

override fun onError(e: Throwable) {
    loading(false)
    Snackbar.make(containerRegister, e.message.toString(), Snackbar.LENGTH_LONG).show()
}

override fun onLogin(){
    val intent = Intent(this, LoginActivity::class.java)
    startActivity(intent)
    finish()
}
}