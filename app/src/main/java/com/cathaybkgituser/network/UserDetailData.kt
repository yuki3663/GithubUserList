package com.cathaybkgituser.network

import com.google.gson.annotations.SerializedName


data class UserDetailData(
    val id: Long,

    val login: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    val name: String?,

    val location: String?,

    val blog: String,

    @SerializedName("site_admin")
    val siteAdmin: String,

    val bio: String?
)
