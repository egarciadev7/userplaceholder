package com.test.repository.di

import com.test.repository.remote.http.datasources.PostDataSource
import com.test.repository.remote.http.datasources.UserDataSource
import com.test.repository.remote.http.interfaces.IPostDataSource
import com.test.repository.remote.http.interfaces.IUserDataSource
import com.test.repository.remote.http.services.PostService
import com.test.repository.remote.http.services.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideUserDataSource(api: UserService): IUserDataSource = UserDataSource(api)

    @Singleton
    @Provides
    fun providePostDataSource(api: PostService): IPostDataSource = PostDataSource(api)
}


