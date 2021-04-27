package com.tom.cathaybkgituser.contract

import com.cathaybkgituser.network.UserInfoData

interface ContractInterface {

    interface View {
        fun initView()
        fun updateViewData(list :List<UserInfoData>)
    }

    interface Presenter {
        fun incrementValue()
        fun getCounter(): String
    }

    interface Model {
        fun getCounter(): Int
        fun incrementCounter()
    }

}