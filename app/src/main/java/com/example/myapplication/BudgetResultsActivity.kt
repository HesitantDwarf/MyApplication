package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class BudgetResultsActivity : ComponentActivity() {

    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_budget_results)

        resultTextView = findViewById(R.id.textViewResult)

        resultTextView.text = intent.getStringExtra("RESULT")
    }
}