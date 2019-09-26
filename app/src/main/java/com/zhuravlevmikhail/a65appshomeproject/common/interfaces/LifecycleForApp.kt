package com.zhuravlevmikhail.a65appshomeproject.common.interfaces

import android.app.Activity

interface LifecycleForApp {
    fun onActivityCreate(activity: Activity)
    fun onActivityDestroy()
}