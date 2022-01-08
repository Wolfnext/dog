package com.gacode.dog.view.profile.dogs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPFragment
import com.gacode.dog.view.auth.login.LoginActivity

class DogsActivity : BaseMVPFragment<DogsContract.DogsView, DogsContract.DogsPresenter>(), DogsContract.DogsView {
    override var presenter: DogsContract.DogsPresenter = DogsPresenterImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater!!.inflate(R.layout.activity_dogs, container, false)

        presenter.attachView(this)

        return view
    }

    override fun onLogout() {
        activity?.let {
            val intent = Intent(it, LoginActivity::class.java)
            it.startActivity(intent)
        }
    }

}