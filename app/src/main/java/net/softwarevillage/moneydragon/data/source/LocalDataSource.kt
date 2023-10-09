package net.softwarevillage.moneydragon.data.source

import net.softwarevillage.moneydragon.common.Resource
import net.softwarevillage.moneydragon.data.dto.local.AuthDTO
import net.softwarevillage.moneydragon.data.dto.local.CardDTO
import net.softwarevillage.moneydragon.data.dto.local.TransactionDTO

interface LocalDataSource {

    suspend fun insertTransaction(transactionDTO: TransactionDTO)

    suspend fun insertAuth(authDTO: AuthDTO)

    suspend fun insertCard(cardDTO: CardDTO)

    suspend fun updateBalance(balance: Double)

    suspend fun updateUserPhoto(image: ByteArray?)

    suspend fun isCardRegistered(): Resource<Boolean>

    suspend fun isTransactionHave(): Resource<Boolean>

    suspend fun getCardDetails(): Resource<CardDTO>

    suspend fun getAuthDetails(): Resource<AuthDTO>

    suspend fun getTransaction(): Resource<List<TransactionDTO>>

}