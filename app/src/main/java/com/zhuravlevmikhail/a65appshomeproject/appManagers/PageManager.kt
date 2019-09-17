package com.zhuravlevmikhail.a65appshomeproject.appManagers

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.zhuravlevmikhail.a65appshomeproject.common.LifecyclesForApp

interface PageManagerInterface {

    fun onCreate(activity: AppCompatActivity)
    fun onResume(activity: AppCompatActivity)
    fun onPause()
    fun onDestroy()

}

class PageManager(private val _lifecyclesForApp: LifecyclesForApp) : PageManagerInterface {

    private var _activity: AppCompatActivity? = null
    private lateinit var _fragmentManager: FragmentManager

    override fun onCreate(activity: AppCompatActivity) {
         _lifecyclesForApp.onActivityCreate(activity)
    }

    override fun onResume(activity: AppCompatActivity) {
        _fragmentManager = activity.supportFragmentManager
        _activity = activity
    }

    override fun onPause() {
        _activity = null
    }

    override fun onDestroy() {
        _lifecyclesForApp.onActivityDestroy()
    }
}