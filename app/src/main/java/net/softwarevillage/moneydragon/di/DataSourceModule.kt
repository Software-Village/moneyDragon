package net.softwarevillage.moneydragon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.softwarevillage.moneydragon.data.service.local.RoomDAO
import net.softwarevillage.moneydragon.data.source.LocalDataSource
import net.softwarevillage.moneydragon.data.source.LocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun injectDataSource(roomDAO: RoomDAO) = LocalDataSourceImpl(roomDAO) as LocalDataSource


}