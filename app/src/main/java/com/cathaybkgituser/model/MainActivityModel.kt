package com.tom.cathaybkgituser.model

import com.tom.cathaybkgituser.contract.ContractInterface.*

class MainActivityModel: Model {

    private var mCounter = 0

    override fun getCounter()= mCounter

    override fun incrementCounter() {
        mCounter++
    }
}
data class UserData(val avatar_url: String,
                    val name: String,
                    val bio: String,
                    val login: String,
                    val site_admin: String,
                    val location: String,
                    val blog: String)