package net.softwarevillage.moneydragon.common.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.room.TypeConverter
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.GroupBar
import com.google.gson.GsonBuilder
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import java.io.ByteArrayOutputStream

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


fun totalTransactionUiModel(data: List<TransactionUiModel>, type: Int): TransactionUiModel {
    return if (data.isEmpty()) {
        TransactionUiModel(
            1, 0, "", "", "0.0", type
        )
    } else {
        val total = data.sumOf { it.amount.toDouble() }
        val uiData = data.first()
        TransactionUiModel(
            1, uiData.category, uiData.title, uiData.date, total.toFloat().toString(), type
        )
    }
}

fun totalTransactionAmount(data: List<TransactionUiModel>): Double {
    return if (data.isEmpty()) 0.0 else data.sumOf { it.amount.toDouble() }
}

fun takeMainMailFromEmail(email: String): String {
    var newMail = ""
    for (c in email) if (c != '@') newMail += c else break
    return newMail
}

fun getBitmapFromUri(context: Context, imageUri: Uri): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(imageUri)
        inputStream.use {
            BitmapFactory.decodeStream(it)
        }
    } catch (e: Exception) {
        null
    }
}

@TypeConverter
fun fromUri(context: Context, image: Uri): ByteArray {
    val bitmap = getBitmapFromUri(context, image)
    val outputStream = ByteArrayOutputStream()
    bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    return outputStream.toByteArray()
}


@TypeConverter
fun toBitmap(byteArray: ByteArray?): Bitmap? {
    return byteArray?.let { BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size) }
}