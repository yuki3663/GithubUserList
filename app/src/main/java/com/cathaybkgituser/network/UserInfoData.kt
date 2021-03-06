package com.cathaybkgituser.network

import com.google.gson.annotations.SerializedName

data class UserInfoData(

    val id: Long,

    val login: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("site_admin")
    val siteAdmin: String
)
