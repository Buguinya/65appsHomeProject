package com.zhuravlevmikhail.a65appshomeproject.appManagers

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
import com.zhuravlevmikhail.a65appshomeproject.core.fragm_contact.ContactsView
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
    fun onBackPressed()

    /** NAVIGATION */
    fun setContactsPage()
}

class PageManager(private val _lifecyclesForApp: LifecyclesForApp) : PageManagerInterface {

    private var _activity: AppCompatActivity? = null
    private lateinit var _fragmentManager: FragmentManager

    override fun onCreate(activity: AppCompatActivity) {
         _fragmentManager = activity.supportFragmentManager
         _lifecyclesForApp.onActivityCreate(activity)
    }

    override fun onResume(activity: AppCompatActivity) {
        _activity = activity
    }

    override fun onPause() {
        _activity = null
    }

    override fun onDestroy() {
        _lifecyclesForApp.onActivityDestroy()
    }

    override fun showSnackBar(message: String) {
        if (isActivityPaused()) return
        Snackbar.make(_activity!!.fragmentsContainer, message, Snackbar.LENGTH_LONG).show()
    }

    override fun showToast(message: String) {
        if (isActivityPaused()) return
        getToastLong(message).show()
    }

    override fun showTopToast(message: String) {
        if (isActivityPaused()) return
        val toastShort = getToastShort(message)
        val px = Utils.getPxInDp(16f, _activity!!.resources)
        toastShort.setGravity(Gravity.TOP or Gravity.START, px, px)
        toastShort.show()
    }

    override fun onBackPressed() {
        _fragmentManager.popBackStack()
        Handler().postDelayed({
            _activity?.let {
                Utils.hideKeyboard(it)
            }
        }, 300)
    }

    override fun setContactsPage() {
        val page = ContactsView()
        page.configure(R.layout.fragm_contacts_list, this)
        setPage(page)
    }

    private fun setPage(fragment: Fragment) {
        _fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        _fragmentManager.beginTransaction().
            setCustomAnimations(R.anim.alpha_in, R.anim.alpha_out).
            replace(R.id.fragmentsContainer, fragment).
            commit()
    }

    private fun addPage(fragment: Fragment) {
        val animModel = CustomAnimModel(
            R.anim.slide_in_right, R.anim.slide_out_right,
            R.anim.slide_in_left, R.anim.slide_out_left
        )
        addPageAction(fragment, animModel)
    }

    private fun addPageFromBottom(fragment: Fragment) {
        val animModel = CustomAnimModel(
            R.anim.slide_up_from_bottom, R.anim.layout_hold_position,
            R.anim.layout_hold_position, R.anim.slide_down_from_bottom
        )
        addPageAction(fragment, animModel)
    }

    private fun addPageImmediately(fragment: Fragment) {
        addPageAction(fragment)
    }

    private fun addPageAction(fragment: Fragment, animModel: CustomAnimModel? = null) {
        if (isActivityPaused()) return
        val fragmentName = Utils.getClassName(fragment)
        if (isFragmentAddBanned(fragmentName)) {
            return
        }

        val fragmTransaction = _fragmentManager.beginTransaction()

        animModel?.let {
            fragmTransaction.setCustomAnimations(it.enter, it.exit, it.popEnter, it.popExit)
        }
        fragmTransaction.add(R.id.fragmentsContainer, fragment).addToBackStack(fragmentName)

        fragmTransaction.commit()
    }


    private fun isFragmentAddBanned(fragmentName: String): Boolean {
        var isBanned = false
        val lastFragment = _fragmentManager.fragments.last()
        lastFragment?.let {
            isBanned = Utils.getClassName(it) == fragmentName
        }
        return isBanned
    }

    private fun getToastShort(message: String): Toast {
        return Toast.makeText(_activity!!, message, Toast.LENGTH_SHORT)
    }

    private fun getToastLong(message: String): Toast {
        return Toast.makeText(_activity!!, message, Toast.LENGTH_LONG)
    }

    private fun isActivityPaused(): Boolean {
        return _activity == null
    }
}