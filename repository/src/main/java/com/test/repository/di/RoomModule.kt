package com.test.repository.di

import android.content.Context
import androidx.room.Room
import com.test.repository.local.database.AppDataBase
import com.test.repository.local.database.dao.UserDao
import com.test.repository.local.database.datasources.UserRoomDataSource
import com.test.repository.local.database.interfaces.IUserLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDataBase =
        Room.databaseBuilder(applicationContext, AppDataBase::class.java, "AppDataBase")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideUserDao(database: AppDataBase): UserDao = database.userDao

    @Singleton
    @Provides
    fun provideUserRoom(userDao: UserDao): IUserLocalDataSource = UserRoomDataSource(userDao)
}


