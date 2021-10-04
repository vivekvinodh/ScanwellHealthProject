package com.example.scanwellhealthproject.data.sources.remote

import com.example.scanwellhealthproject.networking.UserService
import com.example.scanwellhealthproject.models.UserResponse
import com.example.scanwellhealthproject.models.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class UserRemoteSource @Inject constructor(private val retrofit: Retrofit) {

    /**
     * Returns the result of the getUsers() API call in a [UserResponse] object,
     * wrapped in a [Result]
     *
     * @return Result<UserResponse>
     */
    suspend fun fetchUsers(): Flow<Result<UserResponse>> {
        val userService = retrofit.create(UserService::class.java)
        return flow {
            emit(getResponse(request = {userService.getUsers()}))
        }
    }

    /**
     * Higher order kotlin function which takes a retrofit function as its parameter,
     * and returns a retrofit response wrapped in the [Result] class
     *
     * @param request SuspendFunction0<Response<T>>
     * @return Result<T>
     */
    private suspend fun <T> getResponse(request: suspend () -> Response<T>): Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                Result.success(result.body())
            } else {
                val errorResponse = result.errorBody().toString()
                Result.error(errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("Unknown Error")
        }
    }
}