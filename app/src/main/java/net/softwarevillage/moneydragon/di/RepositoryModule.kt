package net.softwarevillage.moneydragon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.softwarevillage.moneydragon.data.repository.LocalRepositoryImpl
import net.softwarevillage.moneydragon.data.source.LocalDataSource
import net.softwarevillage.moneydragon.domain.repository.LocalRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun injectRepo(source: LocalDataSource) = LocalRepositoryImpl(source) as LocalRepository

}