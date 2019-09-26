package com.zhuravlevmikhail.a65appshomeproject.appManagers

import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.common.Utils
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.LifecyclesForApp
import com.zhuravlevmikhail.a65appshomeproject.common.models.CustomAnimModel
import com.zhuravlevmikhail.a65appshomeproject.core.detail.DetailedView
import com.zhuravlevmikhail.a65appshomeproject.core.detail.FRAGMENT_DATA_KEY_CONTACT_ID
import com.zhuravlevmikhail.a65appshomeproject.core.contacts.ContactsView
import kotlinx.android.synthetic.main.activity_host.*

interface PageManagerInterface {

    /** LIFECYCLE */
    fun onCreate(activity: AppCompatActivity)
    fun onResume(activity: AppCompatActivity)
    fun onPause()
    fun onDestroy()

    /** HELPERS */
    fun showSnackBar(message: String)
    fun showToast(message: String)
    fun showTopToast(message: String)

    /** CALLBACKS */
    fun onBackPressed()
}

class PageManager(private val lifecyclesForApp: LifecyclesForApp) : PageManagerInterface {

    private var activity: AppCompatActivity? = null
    private var fragmentManager: FragmentManager? = null

    override fun onCreate(activity: AppCompatActivity) {
        lifecyclesForApp.onActivityCreate(activity)
        fragmentManager = activity.supportFragmentManager
    }

    override fun onResume(activity: AppCompatActivity) {
        fragmentManager = activity.supportFragmentManager
        this.activity = activity
    }

    override fun onPause() {
        fragmentManager = null
        activity = null
    }

    override fun onDestroy() {
        lifecyclesForApp.onActivityDestroy()
    }

    override fun showSnackBar(message: String) {
        if (isActivityPaused()) return
        activity?.let {
            Snackbar.make(it.fragmentsContainer, message, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun showToast(message: String) {
        if (isActivityPaused()) return
        getToastLong(message).show()
    }

    override fun showTopToast(message: String) {
        if (isActivityPaused()) return
        activity?.let {
            val toastShort = getToastShort(message)
            val px = Utils.getPxInDp(16f, it.resources)
            toastShort.setGravity(Gravity.TOP or Gravity.START, px, px)
            toastShort.show()
        }
    }

    override fun onBackPressed() {
        fragmentManager?.popBackStack()
    }


    private fun getToastShort(message: String): Toast {
        return Toast.makeText(activity, message, Toast.LENGTH_SHORT)
    }

    private fun getToastLong(message: String): Toast {
        return Toast.makeText(activity, message, Toast.LENGTH_LONG)
    }

    private fun isActivityPaused(): Boolean {
        return activity == null
    }
}