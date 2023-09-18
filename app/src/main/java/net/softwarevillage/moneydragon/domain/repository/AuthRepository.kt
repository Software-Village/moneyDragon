package net.softwarevillage.moneydragon.domain.repository

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import net.softwarevillage.moneydragon.common.Resource

interface AuthRepository {

    suspend fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>

    suspend fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>

}