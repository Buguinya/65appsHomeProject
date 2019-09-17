package com.zhuravlevmikhail.a65appshomeproject.common

import android.app.Activity
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager

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

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }

        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}