package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {

    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton

    lateinit var costServiceText: EditText

    lateinit var amountText: TextView

    lateinit var switchCase: SwitchMaterial;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        costServiceText = findViewById<EditText>(R.id.editTextNumber);

        val calculateButton = findViewById<Button>(R.id.button_calculate);

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup);

        amountText = findViewById<TextView>(R.id.text_amount)

        switchCase = findViewById(R.id.switchcase2);

        calculateButton.setOnClickListener {
            val selectionOption = radioGroup!!.checkedRadioButtonId;

            radioButton = findViewById(selectionOption);

            val discountValue: Double = when (radioButton.id) {
                R.id.awesomeRadio -> 0.3
                R.id.okayishRadio -> 0.2
                R.id.nehhRadio -> 0.1

                else -> 0.0
            }

            costOfServiceDisplay(discountValue)

        }

    }


    fun costOfServiceDisplay(discountValue: Double) {
        val serviceAmount = costServiceText.text.toString();

        if (TextUtils.isEmpty(serviceAmount)) {
            return Toast.makeText(baseContext, "You didnt enter anything bro", Toast.LENGTH_LONG)
                .show()
        } else {
            val floatValue = serviceAmount.toFloatOrNull();
            if (floatValue != null) {
                if (discountValue == 0.0) {
//                    amountText.setText("No Tip");
                } else {
                    var finalValue: Double = discountValue * floatValue;

                    if(switchCase.isChecked){
                        finalValue = Math.ceil(finalValue);
                    }



                    amountText.setText(finalValue.toString())
                }

            }
        }
    }
}