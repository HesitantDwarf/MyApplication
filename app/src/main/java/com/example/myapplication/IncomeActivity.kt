package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class IncomeActivity : ComponentActivity() {

    private lateinit var button: Button
    private lateinit var submitButton: Button
    private lateinit var textView: TextView
    private lateinit var incomeCat: EditText
    private lateinit var incomeVal: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_income)

        val categoryMap = HashMap<String, Int>(10)
        var text = ""
        button = findViewById(R.id.existBudgetButton)
        submitButton = findViewById(R.id.newBudgetButton)
        textView = findViewById(R.id.textView)
        incomeCat = findViewById(R.id.incomeCat)
        incomeVal = findViewById(R.id.incomeVal)

        button.setOnClickListener {
            val incomeCatString = incomeCat.text.toString()
            val incomeValString = incomeVal.text.toString()
            if (incomeCat.text.isNotEmpty() and incomeVal.text.isNotEmpty()) {
                if (categoryMap.size < 10) {
                    if (incomeCatString !in categoryMap) {
                        text += "$incomeCatString = $incomeValString\n"
                        categoryMap[incomeCatString] = incomeValString.toInt()
                        textView.text = text
                    } else textView.text = "Category already exists."
                } else textView.text = "Categories full."
            } else textView.text = "Please enter a category."
            incomeCat.setText("")
            incomeVal.setText("")
        }

        submitButton.setOnClickListener {
            val intent = Intent(this, ExpensesActivity::class.java).apply {
                putExtra("INCOMES", categoryMap)
            }
            startActivity(intent)
        }
    }
}