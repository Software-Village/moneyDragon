package net.softwarevillage.moneydragon.data.dto.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserCard")
data class CardDTO(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val holdersName: String,
    val cardScheme: String,
    val cardNumber: String,
    val expiryDate: String,
    val cvv: Int,
    val cardColor: String,
    val balance: Double,
)
