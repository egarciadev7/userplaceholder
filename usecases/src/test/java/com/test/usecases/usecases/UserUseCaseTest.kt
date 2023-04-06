package com.test.usecases.usecases

import com.test.entities.ObjectResult
import com.test.entities.User
import com.test.repository.local.database.interfaces.IUserLocalDataSource
import com.test.repository.remote.http.interfaces.IUserDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UserUseCaseTest {
    @RelaxedMockK
    private lateinit var userRemoteDataSource: IUserDataSource

    @RelaxedMockK
    private lateinit var userRoomDataSource: IUserLocalDataSource

    lateinit var userUseCase: UserUseCase

    private val mockUsers = listOf(
        User(1, "test", "userTest", "user1@gmail.com", "31544777"),
        User(2, "test2", "userTest2", "user2@gmail.com", "31544778")
    )

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        userUseCase = UserUseCase(userRemoteDataSource, userRoomDataSource)
    }

    @Test
    fun getUserListFromBackend() = runBlocking {
        //Given
        coEvery { userRoomDataSource.getUserList() } returns emptyList()
        coEvery { userRemoteDataSource.getUserList() } returns ObjectResult.Success(mockUsers)

        //When
        val userUseCaseResponse = userUseCase.getUserList()
        val localUsers = userRoomDataSource.getUserList()

        //Then
        coVerify(exactly = 1) { userRemoteDataSource.getUserList() }
        coVerify(exactly = 0) { userRoomDataSource.getUsersListByTerm(any()) }
        assert(localUsers.isEmpty())
        assert(userUseCaseResponse is ObjectResult.Success)
    }

    @Test
    fun getUserListFromLocal() = runBlocking {
        //Given
        coEvery { userRoomDataSource.getUserList() } returns mockUsers

        //When
        val userUseCaseResponse = userUseCase.getUserList()

        //Then
        coVerify(exactly = 0) { userRemoteDataSource.getUserList() }
        assert(userUseCaseResponse is ObjectResult.Success)
    }

    @Test
    fun getUserListWithSearchTerm() = runBlocking {
        //Given
        coEvery { userRoomDataSource.getUsersListByTerm("test") } returns mockUsers

        //When
        val userUseCaseResponse = userUseCase.getUserList()

        //Then
        coVerify(exactly = 0) { userRemoteDataSource.getUserList() }
        coVerify(exactly = 0) { userRoomDataSource.getUserList() }
        assert(userUseCaseResponse is ObjectResult.Success)
    }
}