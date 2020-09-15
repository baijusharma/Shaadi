package com.demo.shaadi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userInfo")
data class UserInfo (
    @PrimaryKey(autoGenerate = true)
    val id: Int?= null,
    val title: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val gender: String = "",
    val city: String = "",
    val country: String = "",
    val age: Int =0,
    val image: String = "",
    val uuid: String = "",
)
