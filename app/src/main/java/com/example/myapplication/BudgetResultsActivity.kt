package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.graphics.Color
import android.graphics.Typeface
import android.widget.Button
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF

class BudgetResultsActivity : ComponentActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var pieChart: PieChart
    private lateinit var goBackButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_budget_results)

        resultTextView = findViewById(R.id.textViewResult)
        pieChart = findViewById(R.id.pieChart)
        goBackButton = findViewById(R.id.goBack)

        pieChart.description.isEnabled = false
        pieChart.dragDecelerationFrictionCoef = 0.95f
        pieChart.isDrawHoleEnabled = true
        pieChart.setTransparentCircleAlpha(110)
        pieChart.holeRadius = 58f
        pieChart.transparentCircleRadius = 61f
        pieChart.setDrawCenterText(true)
        pieChart.setCenterTextColor(R.color.black)
        pieChart.centerText = "$" + intent.getFloatExtra("INCOME", 0f).toString()
        pieChart.setRotationAngle(0f)
        pieChart.isRotationEnabled = true
        pieChart.isHighlightPerTapEnabled = true
        pieChart.animateY(1400, Easing.EaseInOutQuad)
        pieChart.legend.isEnabled = false
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.setEntryLabelTextSize(12f)

        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(intent.getFloatExtra("MORTGAGE", 0f)))
        entries.add(PieEntry(intent.getFloatExtra("POWER", 0f)))
        entries.add(PieEntry(intent.getFloatExtra("CAR", 0f)))
        entries.add(PieEntry(intent.getFloatExtra("FOOD", 0f)))
        entries.add(PieEntry(intent.getFloatExtra("OTHER", 0f)))
        val dataSet = PieDataSet(entries, "Budget.")

        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        val colours: ArrayList<Int> = ArrayList()
        colours.add(resources.getColor(R.color.purple_200))
        colours.add(resources.getColor(R.color.yellow))
        colours.add(resources.getColor(R.color.red))
        colours.add(resources.getColor(R.color.orange))
        colours.add(resources.getColor(R.color.teal_700))
        dataSet.colors = colours

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.DEFAULT_BOLD)
        data.setValueTextColor(Color.BLACK)

        pieChart.setData(data)
        pieChart.highlightValue(null)

        pieChart.invalidate()
        resultTextView.text = intent.getStringExtra("RESULT")

        goBackButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}