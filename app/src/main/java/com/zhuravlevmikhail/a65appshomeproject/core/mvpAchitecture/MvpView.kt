package com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture

import android.os.Bundle
import com.zhuravlevmikhail.a65appshomeproject.appManagers.PageManager

interface MvpView {
    fun configure(layoutId: Int, pageManager: PageManager, fragmentData: HashMap<String, Any>? = null)
    /* Init mvp logic */
    fun firstInit()
    /* Light init's for views before animation */
    fun lightInitViews()
    /* Loading info's after animation */
    fun loadData()

    fun getFragmentData(): Map<String, Any>?

    fun showSnackbar(message: String )
    fun saveInBundle(bundle: Bundle)
    fun freeView()
    fun restoreBundle(bundle: Bundle)
}