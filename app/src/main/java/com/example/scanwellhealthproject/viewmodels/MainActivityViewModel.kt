package com.example.scanwellhealthproject.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scanwellhealthproject.data.UserRepository
import com.example.scanwellhealthproject.models.Result
import com.example.scanwellhealthproject.models.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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

    val userList = MutableLiveData<Result<UserResponse>>()

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            userRepository.fetchUsers().collect {
                userList.value = it
            }
        }
    }

}