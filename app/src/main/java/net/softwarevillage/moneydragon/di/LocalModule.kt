package net.softwarevillage.moneydragon.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.softwarevillage.moneydragon.common.utils.DB_NAME
import net.softwarevillage.moneydragon.data.service.local.RoomDB
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Provides
    @Singleton
    fun injectRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        RoomDB::class.java,
        DB_NAME
    ).build()


    @Provides
    @Singleton
    fun injectRoomDAO(roomDB: RoomDB) = roomDB.getDao()


}