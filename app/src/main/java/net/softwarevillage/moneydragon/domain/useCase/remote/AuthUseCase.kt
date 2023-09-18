package net.softwarevillage.moneydragon.domain.useCase.remote

import net.softwarevillage.moneydragon.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend fun login(email: String, password: String) = authRepository.loginUser(email, password)

    suspend fun register(email: String, password: String) =
        authRepository.registerUser(email, password)

}