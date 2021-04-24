package com.tom.cathaybkgituser.model

import com.tom.cathaybkgituser.contract.ContractInterface.*

class MainActivityModel: Model {

    private var mCounter = 0

    override fun getCounter()= mCounter

    override fun incrementCounter() {
        mCounter++
    }
}