package net.softwarevillage.moneydragon.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.softwarevillage.moneydragon.data.repository.AuthRepositoryImpl
import net.softwarevillage.moneydragon.domain.repository.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    @Singleton
    fun provideAuth(
        firebaseAuth: FirebaseAuth
    ): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }
}