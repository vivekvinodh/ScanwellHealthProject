package com.example.scanwellhealthproject.networking

import com.example.scanwellhealthproject.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    /**
     * retrofit interface for get users from stackoverflow
     *
     * @return Response<UserResponse>
     */
    @GET("/2.2/users?site=stackoverflow")
    suspend fun getUsers() : Response<UserResponse>

}