package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.graphics.Color
import android.graphics.Typeface
import android.widget.Button
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF

class ResultsActivity : ComponentActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var pieChart: PieChart
    private lateinit var goBackButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.results_activity)

        resultTextView = findViewById(R.id.textViewResult)
        pieChart = findViewById(R.id.pieChart)
        goBackButton = findViewById(R.id.goBack)

        val mortgage: Float = intent.getFloatExtra("MORTGAGE", 400f)

        @Suppress("UNCHECKED_CAST")
        val incomesMap: HashMap<String, Int> = intent.getSerializableExtra("INCOMES") as HashMap<String, Int>
        val incomes = incomesMap.toList().sortedByDescending { it.second }.toMap()
        @Suppress("UNCHECKED_CAST")
        val expensesMap: HashMap<String, Int> = intent.getSerializableExtra("EXPENSES") as HashMap<String, Int>
        val expenses = expensesMap.toList().sortedByDescending { it.second }.toMap()

        var totalIncome = 0f
        for ((category, income) in incomes) {
            totalIncome += income
        }

        val entries: ArrayList<PieEntry> = ArrayList()
        var totalExpenses = 0f
        for ((category, expense) in expenses) {
            totalExpenses += expense
            if(expense > 0) entries.add(PieEntry(expense.toFloat(), category))
        }
        val dataSet = PieDataSet(entries, "")

        pieChart.description.isEnabled = false
        pieChart.isRotationEnabled = false
        pieChart.isDrawHoleEnabled = true
        pieChart.setTransparentCircleAlpha(110)
        pieChart.holeRadius = 58f
        pieChart.transparentCircleRadius = 61f
        pieChart.setDrawCenterText(true)
        pieChart.setCenterTextColor(R.color.black)
        pieChart.setCenterTextSize(24f)
        pieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD)
        pieChart.centerText = "$$totalIncome"
        pieChart.rotationAngle - 270f
        pieChart.maxAngle = if(totalExpenses>totalIncome) 360f else if(totalExpenses/totalIncome < 0.25f) 90f else (totalExpenses/totalIncome)*360f
        pieChart.isHighlightPerTapEnabled = true
        pieChart.animateY(2000, Easing.EaseInOutQuad)
        pieChart.setDrawEntryLabels(false)

        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        val colours: ArrayList<Int> = ArrayList()
        colours.add(resources.getColor(R.color.purple_200))
        colours.add(resources.getColor(R.color.red))
        colours.add(resources.getColor(R.color.darkBlue))
        colours.add(resources.getColor(R.color.orange))
        colours.add(resources.getColor(R.color.teal_700))
        colours.add(resources.getColor(R.color.yellow))
        colours.add(resources.getColor(R.color.green))
        colours.add(resources.getColor(R.color.lightBlue))
        colours.add(resources.getColor(R.color.pink))
        colours.add(resources.getColor(R.color.blue))
        colours.subList(0, entries.size)
        dataSet.colors = colours

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.DEFAULT_BOLD)
        data.setValueTextColor(Color.BLACK)

        pieChart.setData(data)
        pieChart.highlightValue(null)

        val legend = pieChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.textSize = 12f
        legend.setDrawInside(false)

        pieChart.invalidate()

        var text = "Income:\n"
        for ((category, value) in incomes) {
            text += "$category: $value\n"
        }
        text += "\nExpenses:\n"
        for ((category, value) in expenses) {
            text += "$category: $value\n"
        }

        resultTextView.text = text

        goBackButton.setOnClickListener {
            finish()
        }
    }
}