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
    fun onContactsAccessGranted()

    /** NAVIGATION */
    fun setContactsPage()
    fun addDetailedContactPage(contactId: Long)
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
        activity = null
        fragmentManager = null
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

    override fun onContactsAccessGranted() {
        val fragment = fragmentManager?.findFragmentById(R.id.fragmentsContainer)
        if (fragment != null && fragment is ContactsView) {
            fragment.onContactsAccessGranted()
        }
    }

    override fun setContactsPage() {
        val page = ContactsView()
        page.configure(R.layout.fragm_contacts_list, this)
        setPage(page)
    }

    override fun addDetailedContactPage(contactId : Long) {
        val page = DetailedView()
        val data = getNewFragmentData()
        data.putLong(FRAGMENT_DATA_KEY_CONTACT_ID, contactId)
        page.arguments = data
        page.configure(R.layout.fragm_con_detailed, this)
        addPage(page)
    }

    private fun setPage(fragment: Fragment) {
        fragmentManager?.let {
            it.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            it.beginTransaction()
                .setCustomAnimations(R.anim.alpha_in, R.anim.alpha_out)
                .replace(R.id.fragmentsContainer, fragment).commit()
        }
    }

    private fun addPage(fragment: Fragment) {
        val animModel = CustomAnimModel(
            R.anim.slide_in_right, R.anim.slide_out_right,
            R.anim.slide_in_left, R.anim.slide_out_left
        )
        addPageAction(fragment, animModel)
    }

    private fun addPageAction(fragment: Fragment, animModel: CustomAnimModel? = null) {
        if (isActivityPaused()) return
        fragmentManager?.let {
            val fragmentName = Utils.getClassName(fragment)
            if (isFragmentAddBanned(fragmentName)) {
                return
            }

            val fragmTransaction = it.beginTransaction()

            animModel?.let {
                fragmTransaction.setCustomAnimations(it.enter, it.exit, it.popEnter, it.popExit)
            }
            fragmTransaction.add(R.id.fragmentsContainer, fragment).addToBackStack(fragmentName)

            fragmTransaction.commit()
        }
    }


    private fun isFragmentAddBanned(fragmentName: String): Boolean {
        var isBanned = false
        val lastFragment = fragmentManager?.fragments?.last()
        lastFragment?.let {
            isBanned = Utils.getClassName(it) == fragmentName
        }
        return isBanned
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

    private fun getNewFragmentData(): Bundle {
        return Bundle()
    }
}