package com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture

import android.os.Bundle

interface MvpView {
    fun configure(layoutId: Int)
    /* Init mvp logic */
    fun firstInit()
    /* Light init's for views before animation */
    fun lightInitViews()
    /* Loading info's after animation */
    fun loadData()
    fun saveInBundle(bundle: Bundle)
    fun freeView()
    fun restoreBundle(bundle: Bundle)
    fun showError(error : String)
    fun showError(error: Int)
}