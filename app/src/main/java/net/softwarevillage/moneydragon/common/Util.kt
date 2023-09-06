package net.softwarevillage.moneydragon.common

import com.google.gson.GsonBuilder

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