package com.example.scanwellhealthproject.viewmodels

import androidx.lifecycle.*
import com.example.scanwellhealthproject.data.UserRepository
import com.example.scanwellhealthproject.models.User
import com.example.scanwellhealthproject.models.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * ViewModel for DetailActivity
 *
 * @property userRepository UserRepository
 * @property id MutableLiveData<Int>
 * @property user LiveData<Result<User>?>
 * @constructor
 */
@HiltViewModel
class DetailActivityViewModel @Inject constructor(private val userRepository: UserRepository)
    : ViewModel() {

    //provides observable live data that is updated when id is updated
    private var id = MutableLiveData<Int>()
    val user : LiveData<Result<User>?> = id.distinctUntilChanged().switchMap {
        liveData {
            userRepository.fetchUser(it).onStart {
                emit(Result.loading())
            }.collect {
                emit(it)
            }
        }
    }

    fun fetchUserDetails(accountId: Int) {
        id.value = accountId
    }

}