package net.softwarevillage.moneydragon.presentation.ui.screens.chart.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.ui.barchart.GroupBarChart
import co.yml.charts.ui.barchart.models.BarPlotData
import co.yml.charts.ui.barchart.models.GroupBarChartData
import net.softwarevillage.moneydragon.common.utils.getGroupBarChartTransactionsData
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.BlueEB

@Composable
fun MainGroupBarTransactionChart(
    modifier: Modifier = Modifier,
    maxRange: Int,
    yStepSize: Int,
    transactionIncoming: List<TransactionUiModel>,
    transactionOutgoing: List<TransactionUiModel>,
) {

    val colorList = listOf(
        BlueEB, Blue
    )

    val groupBarData = getGroupBarChartTransactionsData(
        transactionIncoming, transactionOutgoing
    )

    val groupBarPlotData = BarPlotData(
        groupBarList = groupBarData,
        barColorPaletteList = colorList
    )

    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(groupBarData.size - 1)
        .bottomPadding(40.dp)
        .labelData { index -> groupBarData[index].label }
        .build()

    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()

    val groupBarChartData = GroupBarChartData(
        barPlotData = groupBarPlotData,
        xAxisData = xAxisData,
        yAxisData = yAxisData
    )

    GroupBarChart(
        modifier = modifier,
        groupBarChartData = groupBarChartData
    )

}