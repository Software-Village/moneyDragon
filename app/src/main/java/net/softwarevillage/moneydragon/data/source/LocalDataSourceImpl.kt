package net.softwarevillage.moneydragon.data.source

import net.softwarevillage.moneydragon.common.Resource
import net.softwarevillage.moneydragon.data.dto.local.AuthDTO
import net.softwarevillage.moneydragon.data.dto.local.CardDTO
import net.softwarevillage.moneydragon.data.dto.local.TransactionDTO
import net.softwarevillage.moneydragon.data.service.local.RoomDAO
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val roomDAO: RoomDAO,
) : LocalDataSource {
    override suspend fun insertTransaction(transactionDTO: TransactionDTO) {
        roomDAO.insertTransaction(transactionDTO)
    }

    override suspend fun insertAuth(authDTO: AuthDTO) {
        roomDAO.insertAuth(authDTO)
    }

    override suspend fun insertCard(cardDTO: CardDTO) {
        roomDAO.insertCard(cardDTO)
    }

    override suspend fun isCardRegistered(): Resource<Boolean> {
        return try {
            val response = roomDAO.isCardRegistered()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun isTransactionHave(): Resource<Boolean> {
        return try {
            val response = roomDAO.isTransactionHave()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun getCardDetails(): Resource<CardDTO> {
        return try {
            val response = roomDAO.getCardDetails()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun getAuthDetails(): Resource<AuthDTO> {
        return try {
            val response = roomDAO.getAuthDetails()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun getTransaction(): Resource<List<TransactionDTO>> {
        return try {
            val response = roomDAO.getTransaction()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

}
