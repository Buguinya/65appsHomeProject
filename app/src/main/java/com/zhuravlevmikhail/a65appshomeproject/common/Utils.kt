package com.zhuravlevmikhail.a65appshomeproject.common

import android.content.res.Resources
import android.util.TypedValue

object Utils {

    fun getPxInDp(dp: Float, resources: Resources): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            resources.displayMetrics).toInt()
    }

    fun getClassName(`class`: Any): String {
        return `class`::class.java.simpleName
    }
}