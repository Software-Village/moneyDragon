package net.softwarevillage.moneydragon.domain.model


data class CardUiModel(
    val holdersName: String,
    val cardScheme: String,
    val cardNumber: String,
    val expiryDate: String,
    val cvv: Int,
    val cardColor: String,
    val balance: Double,
    val id: Int,
)
