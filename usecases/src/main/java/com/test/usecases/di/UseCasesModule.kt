package com.test.usecases.di

import com.test.repository.remote.http.interfaces.IUserDataSource
import com.test.usecases.interfaces.IUserUseCase
import com.test.usecases.usecases.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Singleton
    @Provides
    fun provideUserUseCase(
        userDataSource: IUserDataSource,
    ): IUserUseCase = UserUseCase(
        userDataSource,
    )
}
