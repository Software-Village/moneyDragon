package net.softwarevillage.moneydragon.data.service.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.softwarevillage.moneydragon.data.dto.local.AuthDTO
import net.softwarevillage.moneydragon.data.dto.local.CardDTO
import net.softwarevillage.moneydragon.data.dto.local.TransactionDTO

@Dao
interface RoomDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transactionDTO: TransactionDTO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuth(authDTO: AuthDTO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(cardDTO: CardDTO)

    @Query("SELECT * FROM UserCard ORDER BY id ASC LIMIT 1")
    suspend fun getCardDetails(): CardDTO

    @Query("SELECT * FROM Auth ORDER BY id ASC LIMIT 1")
    suspend fun getAuthDetails(): AuthDTO

    @Query("select * from `transaction`")
    suspend fun getTransaction(): List<TransactionDTO>


}