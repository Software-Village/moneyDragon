package net.softwarevillage.moneydragon.domain.useCase.local

import net.softwarevillage.moneydragon.domain.repository.LocalRepository
import javax.inject.Inject

class LocalGetDataUseCase @Inject constructor(private val repo: LocalRepository) {

    suspend fun getCardDetails() = repo.getCardDetails()

    suspend fun getAuthDetails() = repo.getAuthDetails()

    suspend fun getTransactions() = repo.getTransaction()

}