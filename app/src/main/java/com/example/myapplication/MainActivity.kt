package com.example.myapplication

import android.content.Intent
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
    private lateinit var other: EditText
    private lateinit var submitButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        income = findViewById(R.id.editTextIncome)
        mortgageRent = findViewById(R.id.editTextMortgageRent)
        power = findViewById(R.id.editTextPower)
        car = findViewById(R.id.editTextCar)
        food = findViewById(R.id.editTextFood)
        other = findViewById(R.id.editTextOther)
        submitButton = findViewById(R.id.buttonSubmit)

        submitButton.setOnClickListener {
            val income: Int = if(income.text.toString().isEmpty()) 0 else income.text.toString().toInt()
            val mortgageRent: Int = if(mortgageRent.text.toString().isEmpty()) 0 else mortgageRent.text.toString().toInt()
            val power: Int = if(power.text.toString().isEmpty()) 0 else power.text.toString().toInt()
            val car: Int = if(car.text.toString().isEmpty()) 0 else car.text.toString().toInt()
            val food: Int = if(food.text.toString().isEmpty()) 0 else food.text.toString().toInt()

            val expenses: Int = mortgageRent + power + car + food
            val netIncome: Int = income - expenses

            var result = """
                Income: $income
                
                Total expenses: $expenses
                
                Income after expenses: $netIncome
            """.trimIndent()

            if(netIncome > 0) {
                result + "\n\nGood job your making money.".trimIndent()
            } else {
                result + "\n\nOhh no, you spend more than you make.".trimIndent()
            }

            val intent = Intent(this, BudgetResultsActivity::class.java).apply {
                putExtra("RESULT", result)
            }
            startActivity(intent)
        }
    }
}