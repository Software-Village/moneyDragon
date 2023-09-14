package net.softwarevillage.moneydragon.domain.model


data class CardFaceUiModel(
    val holdersName: String,
    val cardScheme: String,
    val cardNumber: String,
    val expiryDate: String,
    val balance: Double,
    val cvv: Int,
)
