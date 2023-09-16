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

    suspend fun getCardDetails(): Flow<Resource<CardUiModel>>

    suspend fun getAuthDetails(): Flow<Resource<AuthUiModel>>

    suspend fun getTransaction(): Flow<Resource<List<TransactionUiModel>>>
}