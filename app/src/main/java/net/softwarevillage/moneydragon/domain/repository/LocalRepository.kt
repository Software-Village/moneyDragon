package net.softwarevillage.moneydragon.domain.repository

import kotlinx.coroutines.flow.Flow
import net.softwarevillage.moneydragon.common.Resource
import net.softwarevillage.moneydragon.data.dto.local.AuthDTO
import net.softwarevillage.moneydragon.data.dto.local.CardDTO
import net.softwarevillage.moneydragon.data.dto.local.TransactionDTO
import net.softwarevillage.moneydragon.domain.model.AuthUiModel
import net.softwarevillage.moneydragon.domain.model.CardUiModel
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel

interface LocalRepository {

    suspend fun insertTransaction(transactionDTO: TransactionDTO)

    suspend fun insertAuth(authDTO: AuthDTO)

    suspend fun insertCard(cardDTO: CardDTO)

    suspend fun updateCard(balance: Double)

    suspend fun updateUserPhoto(image: ByteArray?)

    suspend fun getTransactionDetails(id: Int): Flow<Resource<TransactionUiModel>>

    suspend fun getHighestTransaction(type: Int): Flow<Resource<TransactionUiModel>>

    suspend fun getFilteredTransactions(type: Int): Flow<Resource<List<TransactionUiModel>>>

    suspend fun getTransactionsNewestFirst(): Flow<Resource<List<TransactionUiModel>>>

    suspend fun isCardRegistered(): Flow<Resource<Boolean>>

    suspend fun isTransactionHave(): Flow<Resource<Boolean>>


    suspend fun getCardDetails(): Flow<Resource<CardUiModel>>

    suspend fun getAuthDetails(): Flow<Resource<AuthUiModel>>

    suspend fun getTransaction(): Flow<Resource<List<TransactionUiModel>>>
}