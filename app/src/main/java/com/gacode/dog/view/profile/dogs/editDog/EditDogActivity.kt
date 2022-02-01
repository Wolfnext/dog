package com.gacode.dog.view.profile.dogs.editDog

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPActivity
import com.gacode.dog.model.Dog
import kotlinx.android.synthetic.main.activity_edit_dog.*


class EditDogActivity() : BaseMVPActivity<EditDogContract.EditDogView, EditDogContract.EditDogPresenter>(),
    EditDogContract.EditDogView {

    override var presenter: EditDogContract.EditDogPresenter = EditDogPresenterImpl()
    private var dogData : Dog? = null
    private  val arraySpinner = arrayOf(
    "pies", "suka"
    )


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_dog)


        val s: Spinner = findViewById<View>(R.id.editSpinner_gender) as Spinner
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, arraySpinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        s.setAdapter(adapter)


       this.dogData =  intent.getSerializableExtra("dog") as Dog

       Log.d("dog",this.dogData.toString())
        if(this.dogData != null) updateUI()

        btnDog_update.setOnClickListener { view ->
            update()
            if(this.dogData!!.id == null)presenter.createDog(this, this.dogData!!)
            this.dogData!!.id?.let { presenter.updateDog(this, it, this.dogData!!) }

         }

    }

    fun update() {
        this.dogData?.name  = editDog_name.text.toString()
        this.dogData?.race  = editDog_race.text.toString()
        this.dogData?.size  = editDog_size.text.toString()
        this.dogData?.birth  = editDog_birth.text.toString()
        this.dogData?.desc  = editDog_desc.text.toString()
        this.dogData?.gender  = editSpinner_gender.selectedItem.toString()
    }

    fun updateUI(){
        editDog_name.setText(this.dogData!!.name)
        editDog_race.setText(this.dogData!!.race)
        editDog_size.setText(this.dogData!!.size)
        editDog_desc.setText(this.dogData!!.desc)
        editDog_birth.setText(this.dogData!!.birth)
        editSpinner_gender.setSelection(arraySpinner.indexOf(this.dogData?.gender))

        if(this.dogData?.id == null )editDog.setText("dodawanie")
        else editDog.setText("Edycja")

    }

    override fun onSuccess(dog:Dog) {
        finish();
    }

    override fun onFailed(e: String) {
      //  TODO("Not yet implemented")
    }

    override fun onError(e: Throwable) {
       // TODO("Not yet implemented")
    }

    override fun onLogout() {
        //TODO("Not yet implemented")
    }
}