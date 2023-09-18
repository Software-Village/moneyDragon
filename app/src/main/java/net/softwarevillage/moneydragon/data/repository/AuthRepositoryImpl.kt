package net.softwarevillage.moneydragon.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import net.softwarevillage.moneydragon.common.Resource
import net.softwarevillage.moneydragon.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthRepository {
    override suspend fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> =
        flow {
            emit(Resource.Loading)
            val login = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(login))
        }.catch {
            emit(Resource.Error(it as Exception))
        }

    override suspend fun registerUser(email: String, password: String): Flow<Resource<AuthResult>> =
        flow {
            emit(Resource.Loading)
            val register = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.Success(register))
        }.catch {
            emit(Resource.Error(it as Exception))
        }

}