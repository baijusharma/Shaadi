package com.demo.shaadi.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.shaadi.model.Result
import com.demo.shaadi.model.UserInfo

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userList: ArrayList<UserInfo>)

    @Query("SELECT * FROM userInfo")
    fun posts(): DataSource.Factory<Int, UserInfo>
}