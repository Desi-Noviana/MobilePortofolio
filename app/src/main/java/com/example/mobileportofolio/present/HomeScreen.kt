package com.example.mobileportofolio.present

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.components.Legends
import co.yml.charts.common.extensions.formatToSinglePrecision
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.common.utils.DataUtils.getDonutChartData
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import co.yml.charts.ui.piechart.utils.proportion
import com.example.mobileportofolio.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        val context = LocalContext.current
        LazyColumn(content = {
            items(2){item ->
                when(item){
                    0 -> {
                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = stringResource(R.string.portfolio),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .fillMaxWidth()
                        ){
                            Spacer(modifier = Modifier.height(20.dp))
                            SimpleDonutChart(context, navController)
                        }
                    }
                    /*1 -> {
                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = stringResource(R.string.report),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        SingleLineChartWithGridLines(
                            DataUtils.getLineChartData(
                            12,
                            start = 1,
                            maxRange = 12
                        ))
                    }*/
                }
            }
        })
    }
}

fun getPieChartData(): PieChartData{
    return PieChartData(
        slices = listOf(
            PieChartData.Slice("Tarik Tunai", 55f, Color(0xFFEDDCF5)),
            PieChartData.Slice("Qris Payment", 31f, Color(0xFFE8C9F7)),
            PieChartData.Slice("Top Up", 7.7f, Color(0xFFD9A7F1)),
            PieChartData.Slice("Others", 15f, Color(0xFFC976F0)),
        ),
        plotType = PlotType.Donut
    )
}

fun getLineChartData(listSize: Int, start: Int = 0, maxRange: Int): List<Point> {
    val list = arrayListOf<Point>()
    for (index in 0 until listSize) {
        list.add(
            Point(
                index.toFloat(),
                (start until maxRange).random().toFloat()
            )
        )
    }
    return list
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SimpleDonutChart(
    context: Context,
    navController: NavController
) {
    rememberModalBottomSheetState()
    rememberCoroutineScope()
    val data = getPieChartData()
    // Sum of all the values
    val sumOfValues = data.totalLength

    // Calculate each proportion value
    data.slices.proportion(sumOfValues)
    val pieChartConfig =
        PieChartConfig(
            labelVisible = true,
            strokeWidth = 120f,
            labelColor = Color.Black,
            activeSliceAlpha = .9f,
            isEllipsizeEnabled = true,
            labelTypeface = Typeface.defaultFromStyle(Typeface.BOLD),
            isAnimationEnable = true,
            chartPadding = 25,
            labelFontSize = 42.sp,
        )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        Legends(legendsConfig = DataUtils.getLegendsConfigFromPieChartData(pieChartData = data, 3))
        DonutPieChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            data,
            pieChartConfig
        ) { slice ->
            // Toast.makeText(context, slice.label.split(" ").joinToString("").lowercase(), Toast.LENGTH_SHORT).show()
            navController.navigate(slice.label.split(" ").joinToString("").lowercase())
        }
    }
}

/*
@Composable
private fun SingleLineChartWithGridLines(pointsData: List<Point>) {
    val steps = 5
    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .topPadding(105.dp)
        .steps(pointsData.size - 1)
        .labelData { i -> pointsData[i].x.toInt().toString() }
        .labelAndAxisLinePadding(15.dp)
        .build()
    val yAxisData = AxisData.Builder()
        .steps(steps)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            // Add yMin to get the negative axis values to the scale
            val yMin = pointsData.minOf { it.y }
            val yMax = pointsData.maxOf { it.y }
            val yScale = (yMax - yMin) / steps
            ((i * yScale) + yMin).formatToSinglePrecision()
        }.build()
    val data = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines()
    )
    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = data
    )
}*/
