package com.demo.shaadi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userInfo")
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var gender: String = "",
    var city: String = "",
    var country: String = "",
    var age: String = "",
    var image: String = "",
    var email: String = "",
)
