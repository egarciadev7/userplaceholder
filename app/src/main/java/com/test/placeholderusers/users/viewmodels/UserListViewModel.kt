package com.test.placeholderusers.users.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.entities.ObjectResult
import com.test.entities.User
import com.test.usecases.interfaces.IUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userUseCase: IUserUseCase,
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _notResults = MutableLiveData<Boolean>()
    val notResults: LiveData<Boolean> = _notResults

    fun getUserList() {
        viewModelScope.launch {
            _loading.value = true
            _notResults.value = false
            val response =
                userUseCase.getUserList()
            _loading.value = false
            if (response is ObjectResult.Success) {
                response.data.let { users ->
                    Log.d("_log", "users >>>> $users")
                    if (users.isNotEmpty()) {
                        _users.value = users
                    } else {
                        _notResults.value = true
                    }
                }
            } else if (response is ObjectResult.Failure) {
                _loading.value = false
                Log.d(
                    "_log",
                    "error response for services ${response.exception.localizedMessage}"
                )
            }
        }
    }
}
