package com.demo.shaadi.api

import com.demo.shaadi.model.UserResponse
import com.demo.shaadi.utils.APIName.Companion.userListApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersAPICall {

    @GET(userListApi)
    suspend fun getUsersList(
        @Query("results")
        results: Int = 0,
        @Query("page")
        page: Int = 0
    ): Response<UserResponse>

}