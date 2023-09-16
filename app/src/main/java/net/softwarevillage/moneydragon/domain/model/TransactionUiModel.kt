package net.softwarevillage.moneydragon.domain.model

data class TransactionUiModel(
    val id: Long,
    val category: Int,
    val title: String,
    val date: String,
    val amount: String,
    val type: Int,
)
