package com.cathaybkgituser.repository

import android.util.Log
import com.cathaybkgituser.network.GitHubUsersApi
import com.cathaybkgituser.network.NetworkManager
import com.cathaybkgituser.network.UserDetailData
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserDetailRepository
{
    private val gitHubUsersApi: GitHubUsersApi =
        NetworkManager.provideRetrofit(NetworkManager.provideOkHttpClient()).create(GitHubUsersApi::class.java)

    /*suspend fun fetchUserDetail(
        login: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = flow<UserDetailData> {
        val response = gitHubUsersApi.getUser(login)
        response.suspendOnSuccess {
            if (data != null) {
                val userDetail: UserDetailData = data ?: UserDetailData(
                    id = 0,
                    login = "",
                    avatarUrl = "",
                    name = "",
                    location = "",
                    link = "",
                    siteAdmin = false
                )
                emit(userDetail)
                onSuccess()
            }
        }.onError {
            onError(message())
        }.onException {
            onError(message())
        }
    }.flowOn(Dispatchers.IO)*/
}