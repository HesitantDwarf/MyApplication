package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class ExpensesActivity : ComponentActivity() {

    private lateinit var spinner: Spinner
    private lateinit var button: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.expenses_activity)

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
    }
}