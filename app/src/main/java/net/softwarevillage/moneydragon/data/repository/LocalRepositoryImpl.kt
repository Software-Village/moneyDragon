package net.softwarevillage.moneydragon.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.softwarevillage.moneydragon.common.Resource
import net.softwarevillage.moneydragon.data.dto.local.AuthDTO
import net.softwarevillage.moneydragon.data.dto.local.CardDTO
import net.softwarevillage.moneydragon.data.dto.local.TransactionDTO
import net.softwarevillage.moneydragon.data.mapper.toAuthUiModel
import net.softwarevillage.moneydragon.data.mapper.toCardUiModel
import net.softwarevillage.moneydragon.data.mapper.toTransactionUiModel
import net.softwarevillage.moneydragon.data.source.LocalDataSource
import net.softwarevillage.moneydragon.domain.model.AuthUiModel
import net.softwarevillage.moneydragon.domain.model.CardUiModel
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import net.softwarevillage.moneydragon.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val source: LocalDataSource,
) : LocalRepository {
    override suspend fun insertTransaction(transactionDTO: TransactionDTO) {
        source.insertTransaction(transactionDTO)
    }

    override suspend fun insertAuth(authDTO: AuthDTO) {
        source.insertAuth(authDTO)
    }

    override suspend fun insertCard(cardDTO: CardDTO) {
        source.insertCard(cardDTO)
    }

    override suspend fun isCardRegistered(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        when (val response = source.isCardRegistered()) {
            is Resource.Error -> emit(Resource.Error(response.throwable))
            Resource.Loading -> Unit
            is Resource.Success -> emit(Resource.Success(response.result))
        }
    }

    override suspend fun isTransactionHave(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        when (val response = source.isTransactionHave()) {
            is Resource.Error -> emit(Resource.Error(response.throwable))
            Resource.Loading -> Unit
            is Resource.Success -> emit(Resource.Success(response.result))

        }
    }

    override suspend fun getCardDetails(): Flow<Resource<CardUiModel>> = flow {
        emit(Resource.Loading)
        when (val response = source.getCardDetails()) {
            is Resource.Error -> {
                emit(Resource.Error(response.throwable))
            }

            Resource.Loading -> Unit
            is Resource.Success -> {
                emit(Resource.Success(response.result?.toCardUiModel()))
            }
        }
    }

    override suspend fun getAuthDetails(): Flow<Resource<AuthUiModel>> = flow {
        emit(Resource.Loading)
        when (val response = source.getAuthDetails()) {
            is Resource.Error -> {
                emit(Resource.Error(response.throwable))
            }

            Resource.Loading -> Unit
            is Resource.Success -> {
                emit(Resource.Success(response.result?.toAuthUiModel()))
            }
        }
    }

    override suspend fun getTransaction(): Flow<Resource<List<TransactionUiModel>>> = flow {
        emit(Resource.Loading)
        when (val response = source.getTransaction()) {
            is Resource.Error -> {
                emit(Resource.Error(response.throwable))
            }

            Resource.Loading -> Unit
            is Resource.Success -> {
                emit(Resource.Success(response.result?.toTransactionUiModel()))
            }
        }
    }

}