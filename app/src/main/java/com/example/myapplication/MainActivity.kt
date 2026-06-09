package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : ComponentActivity() {

    private lateinit var income: EditText
    private lateinit var expense: EditText
    private lateinit var submitButton: Button
    private lateinit var spinner: Spinner
    private lateinit var button: Button
    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        income = findViewById(R.id.income)
        expense = findViewById(R.id.expense)
        submitButton = findViewById(R.id.buttonSubmit)

        val itemList = arrayListOf("Mortgage", "Rent", "Rates", "Utilities", "Food", "Transport", "Insurance", "Childcare", "Debt obligations", "Car payments", "Phone", "Medical care", "Pets", "Supplies", "other")
        spinner = findViewById(R.id.spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)
        button.setOnClickListener {
            textView.text = spinner.selectedItem.toString()
            adapter.remove(spinner.selectedItem.toString())
            adapter.notifyDataSetChanged()
        }

        submitButton.setOnClickListener {
            //Fix default values
            val income: Float = if(income.text.toString().isEmpty()) 1000f else income.text.toString().toFloat()
            val mortgageRent: Float = if(expense.text.toString().isEmpty()) 200f else expense.text.toString().toFloat()

            val expenses: Float = mortgageRent
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

            val intent = Intent(this, ExpensesActivity::class.java).apply {
                putExtra("INCOME", income)
                putExtra("MORTGAGE", mortgageRent)
                putExtra("EXPENSES", expenses)
                putExtra("RESULT", result)
            }
            startActivity(intent)
        }
    }
}