package net.softwarevillage.moneydragon.data.dto.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Transaction")
data class TransactionDTO(
    val category: Int,
    val title: String,
    val date: String,
    val amount: String,
    val type: Int,
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
)
