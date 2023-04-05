package com.test.placeholderusers.users.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.entities.ObjectResult
import com.test.entities.Post
import com.test.entities.User
import com.test.usecases.interfaces.IPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val postUseCase: IPostUseCase,
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private val _notResults = MutableLiveData<Boolean>()
    val notResults: LiveData<Boolean> = _notResults

    fun getUserPostsList(userId: Int) {
        viewModelScope.launch {
            _loading.value = true
            _notResults.value = false
            val response =
                postUseCase.getPostByUserId(userId)
            _loading.value = false
            if (response is ObjectResult.Success) {
                response.data.let { posts ->
                    if (posts.isNotEmpty()) {
                        _posts.value = posts
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