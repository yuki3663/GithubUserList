package com.cathaybkgituser.repository

import android.util.Log
import com.cathaybkgituser.network.GitHubUsersApi
import com.cathaybkgituser.network.NetworkManager
import com.cathaybkgituser.network.UserInfoData
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserListRepository
{
    private val TAG: String = this.javaClass.name
    private val gitHubUsersApi: GitHubUsersApi =
        NetworkManager.provideRetrofit(NetworkManager.provideOkHttpClient()).create(GitHubUsersApi::class.java)

    /*suspend fun fetchUserList(
        since : Long,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        val response = gitHubUsersApi.getAllUsers(since)
        response.suspendOnSuccess {
            if (data != null) {
                Log.d(TAG, "Data: $data")
                val userList: List<UserInfoData> = data?: listOf()
                emit(userList)
                onSuccess()
            }
        }.onError {
            onError(message())
        }.onException {
            onError(message())
        }
    }.flowOn(Dispatchers.IO)*/
}