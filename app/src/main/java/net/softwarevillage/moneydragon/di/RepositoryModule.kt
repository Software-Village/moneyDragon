package net.softwarevillage.moneydragon.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.softwarevillage.moneydragon.data.repository.MoneyRepositoryImpl
import net.softwarevillage.moneydragon.domain.repository.MoneyRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideRepositoryModule(repositoryImpl: MoneyRepositoryImpl): MoneyRepository

}