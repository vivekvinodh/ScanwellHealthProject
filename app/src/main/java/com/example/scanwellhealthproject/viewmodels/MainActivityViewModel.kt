package com.example.scanwellhealthproject.viewmodels

import androidx.lifecycle.*
import com.example.scanwellhealthproject.data.UserRepository
import com.example.scanwellhealthproject.models.Result
import com.example.scanwellhealthproject.models.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

/**
 * ViewModel for MainActivity
 * @property userRepository UserRepository
 * @property userList MutableLiveData<Result<UserResponse>>
 * @constructor
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(private val userRepository: UserRepository)
    : ViewModel() {

    private val _userList = MutableLiveData<Result<UserResponse>>()
    lateinit var userList : LiveData<Result<UserResponse>>

    init {
        getUsers()
        viewModelScope.launch {
            userList = userRepository.fetchUsersFlow().asLiveData()
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            userRepository.fetchUsersFlow().collect {
                _userList.value = it
            }
        }
    }

}