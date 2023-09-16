package net.softwarevillage.moneydragon.data.service.local

import androidx.room.Database
import androidx.room.RoomDatabase
import net.softwarevillage.moneydragon.data.dto.local.AuthDTO
import net.softwarevillage.moneydragon.data.dto.local.CardDTO
import net.softwarevillage.moneydragon.data.dto.local.TransactionDTO

@Database(entities = [AuthDTO::class, CardDTO::class, TransactionDTO::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun getDao(): RoomDAO
}