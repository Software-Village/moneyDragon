package net.softwarevillage.moneydragon.domain.useCase.local

import net.softwarevillage.moneydragon.domain.repository.LocalRepository
import javax.inject.Inject

class GetLocalDataUseCase @Inject constructor(private val repo: LocalRepository) {

    suspend fun isCardRegistered() = repo.isCardRegistered()

    suspend fun isTransactionHave() = repo.isTransactionHave()

    suspend fun getTransactionDetails(id: Int) = repo.getTransactionDetails(id)

    suspend fun getHighestTransaction(type: Int) = repo.getHighestTransaction(type)

    suspend fun getCardDetails() = repo.getCardDetails()

    suspend fun getAuthDetails() = repo.getAuthDetails()

    suspend fun getTransactions() = repo.getTransaction()

}