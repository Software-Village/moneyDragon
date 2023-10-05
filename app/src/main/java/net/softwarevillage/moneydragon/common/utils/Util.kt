package net.softwarevillage.moneydragon.common.utils

import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.GroupBar
import com.google.gson.GsonBuilder
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel

fun objectToJson(data: Any): String {
    val gson = GsonBuilder().create()
    return gson.toJson(data)
}

fun cardNumberHider(cardNumber: String): String {
    var hidedCardNumber = ""


    cardNumber.forEachIndexed { index, c ->
        if (index <= 11) {
            hidedCardNumber += "*"
        } else {
            hidedCardNumber += c
        }
        if (index % 4 == 3 && index != 0) {
            hidedCardNumber += " "
        }
    }
    return hidedCardNumber
}


fun getGroupBarChartTransactionsData(
    transactionIncoming: List<TransactionUiModel>,
    transactionOutgoing: List<TransactionUiModel>,
): List<GroupBar> {

    val listSize =
        if (transactionOutgoing.size > transactionIncoming.size) transactionOutgoing.size else transactionIncoming.size

    val list = mutableListOf<GroupBar>()

    for (index in 0 until listSize) {
        val barList = mutableListOf<BarData>()

        val barValueIncoming = "%.2f".format(
            if (index > transactionIncoming.size - 1) 0.0 else transactionIncoming[index].amount.toDouble()
        ).toFloat()

        val barValueOutgoing = "%.2f".format(
            if (index > transactionOutgoing.size - 1) 0.0 else transactionOutgoing[index].amount.toDouble()
        ).toFloat()

        barList.add(
            BarData(
                Point(
                    index.toFloat(),
                    barValueIncoming
                ),
                label = "B1",
                description = "Bar at $index with label B1 has value ${
                    String.format(
                        "%.2f", barValueIncoming
                    )
                }"
            )
        )
        barList.add(
            BarData(
                Point(
                    index.toFloat(),
                    barValueOutgoing
                ),
                label = "B1",
                description = "Bar at $index with label B1 has value ${
                    String.format(
                        "%.2f", barValueOutgoing
                    )
                }"
            )
        )


        list.add(GroupBar(index.toString(), barList))
    }
    return list
}


fun totalTransactionUiModel(data: List<TransactionUiModel>): TransactionUiModel {
    val total = data.sumOf { it.amount.toDouble() }
    val uiData = data.first()
    return TransactionUiModel(
        1, uiData.category, uiData.title, uiData.date, total.toFloat().toString(), uiData.type
    )
}

fun totalTransactionAmount(data: List<TransactionUiModel>): Double {
    return if (data.isEmpty()) 0.0 else data.sumOf { it.amount.toDouble() }
}

fun takeMainMailFromEmail(email: String): String {
    var newMail = ""
    for (c in email) if (c != '@') newMail += c else break
    return newMail
}