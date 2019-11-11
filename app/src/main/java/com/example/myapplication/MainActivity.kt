package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculateButton: Button = findViewById(R.id.buttonCalculate)

        calculateButton.setOnClickListener{
            calculateBMI()
        }
        buttonReset.setOnClickListener{
            reset()
        }
    }

    private fun reset() {
        editTextWeight.setText("")
        editTextHeight.setText("")
        textViewBMI.setText(getString(R.string.bmi))
    }

    private fun calculateBMI() {
        //Validation ensure user enter something
        if(editTextWeight.text.isEmpty()) {
            editTextWeight.setError(getString(R.string.input_error))
            return //stop function
        }
        if(editTextHeight.text.isEmpty()){
            editTextHeight.setError(getString(R.string.input_error))
            return //stop function
        }
        val weight = editTextWeight.text.toString().toFloat()
        val height = editTextHeight.text.toString().toFloat()

        val bmi = weight/((height/100).pow(2))
        if(bmi < 18.5){
            textViewBMI.text = String.format("%s %.2f (%s)",
                getString(R.string.bmi),bmi,getString(R.string.under))
            profile_pic.setImageResource((R.drawable.under))

        }else if(bmi in 18.5..24.5){
            textViewBMI.text = String.format("%s %.2f (%s)",
                getString(R.string.bmi),bmi, getString(R.string.normal))
            profile_pic.setImageResource((R.drawable.normal))
        }else{
            textViewBMI.text = String.format("%s %.2f (%s)",
                getString(R.string.bmi), bmi,getString(R.string.over))
            profile_pic.setImageResource((R.drawable.over))
        }
    }
}
