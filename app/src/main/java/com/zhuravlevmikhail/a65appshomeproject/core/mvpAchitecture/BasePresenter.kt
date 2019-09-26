package com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture

abstract class BasePresenter< V : MvpView> :
    MvpPresenter<V> {

    protected var mvpView: V? = null

    val isViewAttached: Boolean
        get() = mvpView != null

    override fun attachView(view: V) {
        mvpView = view
    }

    override fun lightInitViews() {}

    override fun loadData() {}

    override fun detachView() {
        mvpView = null
    }

    override fun destroy() {
        mvpView = null
    }
}