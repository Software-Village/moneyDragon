package net.softwarevillage.moneydragon.domain.model


data class AuthUiModel(
    val id: Int,
    val name: String,
    val email: String,
    val image: ByteArray?,
)
