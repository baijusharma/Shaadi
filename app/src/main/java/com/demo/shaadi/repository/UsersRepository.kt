package com.demo.shaadi.repository

import com.demo.shaadi.dao.AppDatabase
import com.demo.shaadi.model.UserInfo

class UsersRepository(val db: AppDatabase) {

    /**
     *  Insert user data in DB
     */
     suspend fun insert(userList: MutableList<UserInfo>) {
         db.userDao().insert(userList)
     }

    /**
     *   Get user list  data stored in DB
     */
    fun posts() = db.userDao().posts()

    suspend fun updateUserState(email: String, userState: Int){
        db.userDao().updateUserState(email,userState)
    }
}