package com.demo.shaadi.repository

import com.demo.shaadi.api.RetrofitInstance
import com.demo.shaadi.dao.AppDatabase
import com.demo.shaadi.model.UserResponse
import retrofit2.Response

class UsersRepository(val db: AppDatabase){

    suspend fun getUsersList(result: Int, pageNumber: Int): Response<UserResponse> {
        return RetrofitInstance.api.getUsersList(result, pageNumber)
    }
}