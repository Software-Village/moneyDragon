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

    @Query("update UserCard set balance=:balance")
    suspend fun updateBalance(balance: Double)

    @Query("select * from `transaction` where id=:id")
    suspend fun getTransactionDetails(id: Int): TransactionDTO

    @Query("Select * from `transaction` where type=:type order by amount asc")
    suspend fun getHighestTransaction(type: Int): TransactionDTO

    @Query("Select * from `transaction` where type=:type")
    suspend fun getFilteredTransactions(type: Int): List<TransactionDTO>

    @Query("update Auth set image=:image")
    suspend fun updateUserPhoto(image: ByteArray?)

    @Query("select exists(select * from UserCard)")
    suspend fun isCardRegistered(): Boolean

    @Query("select exists(select * from `Transaction`)")
    suspend fun isTransactionHave(): Boolean

    @Query("SELECT * FROM UserCard ORDER BY id ASC LIMIT 1")
    suspend fun getCardDetails(): CardDTO

    @Query("SELECT * FROM Auth ORDER BY id ASC LIMIT 1")
    suspend fun getAuthDetails(): AuthDTO

    @Query("select * from `transaction`")
    suspend fun getTransaction(): List<TransactionDTO>


    @Query("select * from `transaction` order by id asc")
    suspend fun getTransactionNewestFirst(): List<TransactionDTO>

}