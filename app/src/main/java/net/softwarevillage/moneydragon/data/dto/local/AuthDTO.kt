package net.softwarevillage.moneydragon.data.dto.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Auth")
data class AuthDTO(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val email: String,
    val image: ByteArray?,
)
