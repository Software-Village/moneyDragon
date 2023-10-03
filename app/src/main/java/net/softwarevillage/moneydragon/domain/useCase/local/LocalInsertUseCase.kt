package net.softwarevillage.moneydragon.domain.useCase.local

import net.softwarevillage.moneydragon.data.dto.local.AuthDTO
import net.softwarevillage.moneydragon.data.dto.local.CardDTO
import net.softwarevillage.moneydragon.data.dto.local.TransactionDTO
import net.softwarevillage.moneydragon.domain.repository.LocalRepository
import javax.inject.Inject

class LocalInsertUseCase @Inject constructor(
    private val repo: LocalRepository,
) {

    suspend fun updateBalance(balance: Double) = repo.updateCard(balance)

    suspend fun insertAuth(authDTO: AuthDTO) = repo.insertAuth(authDTO)

    suspend fun insertTransaction(transactionDTO: TransactionDTO) =
        repo.insertTransaction(transactionDTO)

    suspend fun insertCard(cardDTO: CardDTO) = repo.insertCard(cardDTO)

}