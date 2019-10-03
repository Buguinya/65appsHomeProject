package com.zhuravlevmikhail.a65appshomeproject.appManagers

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.LifecycleForApp

interface LifecycleManagerInterface {

    /** LIFECYCLE */
    fun onCreate(activity: AppCompatActivity)
    fun onDestroy()
}

class LifecycleManager(private val lifecycleForApp: LifecycleForApp) : LifecycleManagerInterface {

    override fun onCreate(activity: AppCompatActivity) {
        lifecycleForApp.onActivityCreate(activity)
    }

    override fun onDestroy() {
        lifecycleForApp.onActivityDestroy()
    }
}