package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*





class MainActivity : AppCompatActivity() {

    lateinit var myData: PremiumMdl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myData = ViewModelProviders.of(this)
            .get(PremiumMdl::class.java)

        display()

        btnCal.setOnClickListener(){
            myData.result = getPremium()
            display()
        }

        btnReset.setOnClickListener(){
            ans.setText("")
            radGroup.clearCheck()
            spinnerAge.setSelection(0)
            chkSmoker.setChecked(false)
            myData.result = 0.0
        }

        if(savedInstanceState != null){
            myData.result = savedInstanceState.getDouble("Total")
            ans.text = myData.result.toString()
        }
    }

    fun display(){
        ans.setText(getPremium().toString())
    }

    fun getPremium():Double{

        return when(spinnerAge.selectedItemPosition){
            0 -> 60.00
            1 -> 70.00 +
                    (if(radioButton3.isChecked)50.00 else 0.0) +
                    (if(chkSmoker.isChecked) 100.00 else 0.0)
            2 -> 90.00 +
                    (if(radioButton3.isChecked)100.00 else 0.0) +
                    (if(chkSmoker.isChecked) 150.00 else 0.0)
            3 -> 120.00 +
                    (if(radioButton3.isChecked)150.00 else 0.0) +
                    (if(chkSmoker.isChecked) 200.00 else 0.0)
            4 -> 150.00 +
                    (if(radioButton3.isChecked)200.00 else 0.0) +
                    (if(chkSmoker.isChecked) 250.00 else 0.0)
            else -> 150.00 +
                    (if(radioButton3.isChecked)200.00 else 0.0) +
                    (if(chkSmoker.isChecked) 300.00 else 0.0)
        }
    }

    override fun onSaveInstanceState(outState: Bundle){
        outState.putDouble("Total",myData.result)
        super.onSaveInstanceState(outState)
    }
}