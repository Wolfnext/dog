package com.gacode.dog.view.profile.services.editService

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.gacode.dog.R
import com.gacode.dog.base.BaseMVPActivity
import com.gacode.dog.model.Dog
import com.gacode.dog.model.Service
import com.gacode.dog.view.profile.dogs.editDog.EditDogContract
import com.gacode.dog.view.profile.dogs.editDog.EditDogPresenterImpl
import com.google.android.gms.common.server.converter.StringToIntConverter
import kotlinx.android.synthetic.main.activity_edit_service.*

class EditServiceActivity() : BaseMVPActivity<EditServiceContract.EditServiceView, EditServiceContract.EditServicePresenter>(),
    EditServiceContract.EditServiceView {

    override var presenter: EditServiceContract.EditServicePresenter = EditServicePresenterImpl()
    private var ServiceData : Service? = null
    private  val arraySpinner = arrayOf(
        "Aktywny", "Nieaktywny"
    )


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_service)


        val s: Spinner = findViewById<View>(R.id.editSpinner_active) as Spinner
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, arraySpinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        s.setAdapter(adapter)


        this.ServiceData =  intent.getSerializableExtra("service") as Service
        updateUI()

        btnService_update.setOnClickListener { view ->
            update()
            presenter.updateService(this, this.ServiceData!!)

        }

    }

    fun update() {
        this.ServiceData?.maxSize   = Integer.parseInt(editService_maxSize.text.toString())
        this.ServiceData?.price  = Integer.parseInt(editService_price.text.toString())

        var binaryString : String? = ""
        var binaryStringTime : String? = ""

        if(checkBox_week1.isChecked)binaryString += "1" else binaryString += "0"
        if(checkBox_week2.isChecked)binaryString += "1" else binaryString += "0"
        if(checkBox_week3.isChecked)binaryString += "1" else binaryString += "0"
        if(checkBox_week4.isChecked)binaryString += "1" else binaryString += "0"
        if(checkBox_week5.isChecked)binaryString += "1" else binaryString += "0"
        if(checkBox_week6.isChecked)binaryString += "1" else binaryString += "0"
        if(checkBox_week7.isChecked)binaryString += "1" else binaryString += "0"

        val decimalValue : Int = Integer.parseInt(binaryString,2)

        this.ServiceData?.daysOfWeek = decimalValue;


        if(checkBox_time1.isChecked)binaryStringTime += "1" else binaryStringTime += "0"
        if(checkBox_time2.isChecked)binaryStringTime += "1" else binaryStringTime += "0"
        if(checkBox_time3.isChecked)binaryStringTime += "1" else binaryStringTime += "0"

        val decimalValueTime : Int = Integer.parseInt(binaryStringTime,2)


        this.ServiceData?.time  = decimalValueTime;

        when(editSpinner_active.selectedItem.toString()) {
            "Aktywny" ->    this.ServiceData?.active = "1"
            "Nieaktywny" ->    this.ServiceData?.active = "0"
        }


    }

    fun updateUI(){
        editService_maxSize.setText(this.ServiceData?.maxSize.toString())
        editService_price.setText(this.ServiceData?.price.toString())


        val binaryArrayDaysOfWeek : CharArray = Integer.toBinaryString(this.ServiceData?.daysOfWeek!!).toCharArray()
        val binaryArrayTime : CharArray = Integer.toBinaryString(this.ServiceData?.time!!).toCharArray()

        var textBuilderDaysOfWeek : String = "";
        var textBuilderTime : String = "";

        for (index in binaryArrayDaysOfWeek.indices) {
            if(binaryArrayDaysOfWeek[index] == '1' && index == 0) checkBox_week1.isChecked = true
            if(binaryArrayDaysOfWeek[index] == '1' && index == 1) checkBox_week2.isChecked = true
            if(binaryArrayDaysOfWeek[index] == '1' && index == 2) checkBox_week3.isChecked = true
            if(binaryArrayDaysOfWeek[index] == '1' && index == 3) checkBox_week4.isChecked = true
            if(binaryArrayDaysOfWeek[index] == '1' && index == 4) checkBox_week5.isChecked = true
            if(binaryArrayDaysOfWeek[index] == '1' && index == 5) checkBox_week6.isChecked = true
            if(binaryArrayDaysOfWeek[index] == '1' && index == 6) checkBox_week7.isChecked = true
        }



        for (index in binaryArrayTime.indices) {
            if (binaryArrayTime[index] == '1' && index == 0) checkBox_time1.isChecked = true
            if (binaryArrayTime[index] == '1' && index == 1) checkBox_time1.isChecked = true
            if (binaryArrayTime[index] == '1' && index == 2) checkBox_time1.isChecked = true
        }

        //holder.serviceTime.text = textBuilderTime

        when(this.ServiceData?.active) {
            "1" ->   editSpinner_active.setSelection(arraySpinner.indexOf("Aktywny"))
            "0" ->   editSpinner_active.setSelection(arraySpinner.indexOf("Nieaktywny"))
        }



    }

    override fun onSuccess(Service: Service) {
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


