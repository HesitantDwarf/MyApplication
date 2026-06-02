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
            //Fix default values
            val income: Float = if(income.text.toString().isEmpty()) 1000f else income.text.toString().toFloat()
            val mortgageRent: Float = if(mortgageRent.text.toString().isEmpty()) 200f else mortgageRent.text.toString().toFloat()
            val power: Float = if(power.text.toString().isEmpty()) 50f else power.text.toString().toFloat()
            val car: Float = if(car.text.toString().isEmpty()) 50f else car.text.toString().toFloat()
            val food: Float = if(food.text.toString().isEmpty()) 100f else food.text.toString().toFloat()
            val other: Float = if(other.text.toString().isEmpty()) 50f else other.text.toString().toFloat()

            val expenses: Float = mortgageRent + power + car + food + other
            val netIncome: Float = income - expenses

            val result = """
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
                putExtra("INCOME", income)
                putExtra("MORTGAGE", mortgageRent)
                putExtra("POWER", power)
                putExtra("CAR", car)
                putExtra("FOOD", food)
                putExtra("OTHER", other)
                putExtra("EXPENSES", expenses)
                putExtra("RESULT", result)
            }
            startActivity(intent)
        }
    }
}