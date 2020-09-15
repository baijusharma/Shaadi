package com.demo.shaadi.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val info: Info,
    @SerializedName("results")
    val userData: List<Result>
) {
    data class Info(
        val page: Int = 0,
        val results: Int = 0,
        val seed: String = "",
        val version: String = ""
    )
}