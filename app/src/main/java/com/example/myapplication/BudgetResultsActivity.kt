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

        val income: Float = intent.getFloatExtra("INCOME", 0f)
        val expenses: Float = intent.getFloatExtra("EXPENSES", 0f)
        val mortgage: Float = intent.getFloatExtra("MORTGAGE", 0f)
        val power: Float = intent.getFloatExtra("POWER", 0f)
        val car: Float = intent.getFloatExtra("CAR", 0f)
        val food: Float = intent.getFloatExtra("FOOD", 0f)
        val other: Float = intent.getFloatExtra("OTHER", 0f)

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
        pieChart.centerText = "$" + intent.getFloatExtra("INCOME", 0f).toString()
        pieChart.rotationAngle - 270f
        pieChart.maxAngle = if(expenses>income) 360f else if(expenses/income < 0.25f) 90f else (expenses/income)*360f
        pieChart.isHighlightPerTapEnabled = true
        pieChart.animateY(2000, Easing.EaseInOutQuad)
        pieChart.setDrawEntryLabels(false)

        val entries: ArrayList<PieEntry> = ArrayList()
        if(mortgage>0f) entries.add(PieEntry(mortgage, "Mortgage/Rent"))
        if(power>0f) entries.add(PieEntry(power, "Power"))
        if(car>0f) entries.add(PieEntry(car, "Car"))
        if(food>0f) entries.add(PieEntry(food, "Food"))
        if(other>0f) entries.add(PieEntry(other, "Other"))
        val dataSet = PieDataSet(entries, "")

        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        val colours: ArrayList<Int> = ArrayList()
        colours.add(resources.getColor(R.color.purple_200))
        colours.add(resources.getColor(R.color.red))
        colours.add(resources.getColor(R.color.orange))
        colours.add(resources.getColor(R.color.teal_700))
        colours.add(resources.getColor(R.color.yellow))
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
        resultTextView.text = intent.getStringExtra("RESULT")

        goBackButton.setOnClickListener {
            finish()
        }
    }
}