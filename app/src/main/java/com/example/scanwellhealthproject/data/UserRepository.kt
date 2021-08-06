package com.example.scanwellhealthproject.data

import com.example.scanwellhealthproject.data.sources.local.UserDao
import com.example.scanwellhealthproject.data.sources.remote.UserRemoteSource
import com.example.scanwellhealthproject.models.Result
import com.example.scanwellhealthproject.models.User
import com.example.scanwellhealthproject.models.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(

    private val userRemoteSource: UserRemoteSource,
    private val userLocalSource : UserDao

    ) {

    /**
     * Returns a [Flow] emitting a loading status and then a [User] fetched from the local db
     *
     * @param id Int
     * @return Flow<Result<User>>
     */
    suspend fun fetchUser(id: Int): Flow<Result<User>?> {
        return flow {
            emit(Result.loading())
            emit(Result.success(userLocalSource.getUser(id)))
        }.flowOn(Dispatchers.IO)
    }

    /**
     * Returns a [Flow] emitting any cached users in the db, a loading status, and then the result
     * of the remote data source fetch. If fetch is successful, update users in local db
     *
     * @return Flow<Result<UserResponse>?>
     */
    suspend fun fetchUsers(): Flow<Result<UserResponse>?> {
        return flow {
            emit(fetchCachedUsers())
            emit(Result.loading())
            val result = userRemoteSource.fetchUsers()

            if(result.status == Result.Status.SUCCESS){
                result.data?.items?.let {
                    userLocalSource.deleteAll(it)
                    userLocalSource.insertAll(it)
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)

    }

    /**
     * If there is a list of [User] cached in local db, return in [UserResponse] wrapped
     * in [Result] class
     *
     * @return Result<UserResponse>?
     */
    private fun fetchCachedUsers(): Result<UserResponse>? =
        userLocalSource.getAllUsers()?.let {
            Result.success(UserResponse(false, items = it, 0, 0))
        }




}