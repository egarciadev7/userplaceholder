package com.test.placeholderusers.users.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.entities.ObjectResult
import com.test.entities.User
import com.test.usecases.interfaces.IUserUseCase
import com.test.usecases.usecases.UserUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserListViewModelTest {
    private lateinit var userListViewModel: UserListViewModel

    @RelaxedMockK
    private lateinit var userUseCase: IUserUseCase

    private val mockUsers = listOf(
        User(1, "test", "userTest", "user1@gmail.com", "31544777"),
        User(2, "test2", "userTest2", "user2@gmail.com", "31544778")
    )

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        userListViewModel = UserListViewModel(userUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun viewModelShouldHaveUsers()  = runTest{
        //Given
        coEvery { userUseCase.getUserList() } returns ObjectResult.Success(mockUsers)

        //When
        userListViewModel.getUserList()

        //Then
        assert(userListViewModel.users.value?.size == mockUsers.size)
    }

    @Test
    fun viewModelShouldNotHaveUsers()  = runTest{
        //Given
        coEvery { userUseCase.getUserList() } returns ObjectResult.Success(emptyList())

        //When
        userListViewModel.getUserList()

        //Then
        assert(userListViewModel.notResults.value == true)
    }
}