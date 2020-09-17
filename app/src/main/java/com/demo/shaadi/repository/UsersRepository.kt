package com.demo.shaadi.repository

import com.demo.shaadi.dao.AppDatabase
import com.demo.shaadi.model.UserInfo

class UsersRepository(val db: AppDatabase) {

     suspend fun insert(userList: MutableList<UserInfo>) {
         db.userDao().insert(userList)
     }

    fun posts() = db.userDao().posts()

    suspend fun updateUserState(email: String, userState: Int){
        db.userDao().updateUserState(email,userState)
    }
}