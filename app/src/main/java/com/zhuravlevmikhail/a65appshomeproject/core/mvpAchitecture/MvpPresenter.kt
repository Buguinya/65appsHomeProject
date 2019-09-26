package com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture

interface MvpPresenter<V : MvpView> {
    fun attachView(view: V)
    fun detachView()
    fun lightInitViews()
    fun loadData()
    fun destroy()
}