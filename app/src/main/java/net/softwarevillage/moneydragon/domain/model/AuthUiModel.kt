package net.softwarevillage.moneydragon.domain.model


data class AuthUiModel(
    val id: Int,
    val name: String,
    val surname: String,
    val image: ByteArray?,
    val pin: Int,
)
