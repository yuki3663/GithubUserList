package com.cathaybkgituser.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val GITHUB_BASE_URL = "https://api.github.com/"
const val GITHUB_USER_INITIAL_KEY = 0L
const val GITHUB_USER_PAGE_SIZE = 20

interface GitHubUsersApi {

    @GET("/users")
    fun getAllUsers(@Query("since") since: Long): Call<List<UserInfoData>>

    @GET("/users/{login}")
    fun getUser(@Path("login") login: String): Call<UserDetailData>
}
