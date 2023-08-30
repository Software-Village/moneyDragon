package net.softwarevillage.moneydragon.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.softwarevillage.moneydragon.data.source.LocalDataSource
import net.softwarevillage.moneydragon.data.source.LocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        remoteDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSource

}