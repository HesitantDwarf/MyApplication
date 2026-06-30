package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.widget.Button

class MainActivity : ComponentActivity() {
    private lateinit var newBudgetButton: Button
    private lateinit var existBudgetButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        newBudgetButton = findViewById(R.id.newBudgetButton)
        existBudgetButton = findViewById(R.id.existBudgetButton)

        newBudgetButton.setOnClickListener {
            val intent = Intent(this, IncomeActivity::class.java)
            startActivity(intent)
        }

        existBudgetButton.setOnClickListener {
            //Waiting to add load existing budget feature.
        }
    }
}