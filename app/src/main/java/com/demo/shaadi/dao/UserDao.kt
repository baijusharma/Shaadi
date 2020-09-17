package com.demo.shaadi.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.shaadi.model.UserInfo

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userList: MutableList<UserInfo>)

    @Query("SELECT * FROM userInfo")
    fun posts(): DataSource.Factory<Int, UserInfo>

    @Query("UPDATE userInfo SET userState= :userState WHERE email= :userEmail")
    suspend fun updateUserState(userEmail: String, userState: Int)
}