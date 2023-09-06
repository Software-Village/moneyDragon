package net.softwarevillage.moneydragon.common


fun validateFields(value: List<String>): Boolean {
    var state = true

    for (i in value) {
        if (i.trim().isEmpty()) {
            state = false
            break
        }
    }

    return state
}

fun validateCreditCard(cardNumber: String): Boolean {
    return cardNumber.length > 12
}

fun validateCVV(cvv: String): Boolean {
    return cvv.length == 3
}