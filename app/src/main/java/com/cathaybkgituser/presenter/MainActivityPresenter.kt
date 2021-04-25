package com.tom.cathaybkgituser.presenter

import com.tom.cathaybkgituser.contract.ContractInterface.*
import com.tom.cathaybkgituser.model.MainActivityModel

class MainActivityPresenter(_view: View): Presenter {

    private var view: View = _view
    private var model: Model = MainActivityModel()

    init {
        view.initView()
    }

    override fun incrementValue() {
        model.incrementCounter()
        view.updateViewData()
    }

     override fun getCounter() = model.getCounter().toString()

}