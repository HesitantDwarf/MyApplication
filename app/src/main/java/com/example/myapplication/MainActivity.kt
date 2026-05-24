package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : ComponentActivity() {

    private lateinit var income: EditText
    private lateinit var mortgageRent: EditText
    private lateinit var power: EditText
    private lateinit var car: EditText
    private lateinit var food: EditText
    private lateinit var submitButton: Button
    private lateinit var resultTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        income = findViewById(R.id.editTextIncome)
        mortgageRent = findViewById(R.id.editTextMortgageRent)
        power = findViewById(R.id.editTextPower)
        car = findViewById(R.id.editTextCar)
        food = findViewById(R.id.editTextFood)
        submitButton = findViewById(R.id.buttonSubmit)
        resultTextView = findViewById(R.id.textViewResult)

        submitButton.setOnClickListener {

            val income: Int = income.text.toString().toInt()
            val mortgageRent: Int = mortgageRent.text.toString().toInt()
            val power: Int = power.text.toString().toInt()
            val car: Int = car.text.toString().toInt()
            val food: Int = food.text.toString().toInt()

            val expenses: Int = mortgageRent + power + car + food
            val netIncome: Int = income - expenses

            var result = """
                Income: $income
                
                Total expenses: $expenses
                
                Income after expenses: $netIncome
            """.trimIndent()

            if(netIncome > 0) {
                result = result + "\n\nGood job your making money.".trimIndent()
            } else {
                result = result + "\n\nOhh no, you spend more than you make.".trimIndent()
            }

            resultTextView.text = result
        }
    }
}