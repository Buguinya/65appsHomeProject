package com.zhuravlevmikhail.a65appshomeproject.appManagers

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.LifecyclesForApp
import com.zhuravlevmikhail.a65appshomeproject.common.models.CustomAnimModel

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
    private fun setPage(fragment: Fragment) {
         if (isActivityPaused()) return
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
        val fragmentName = fragment::class.java.simpleName
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
            isBanned = it::class.java.simpleName == fragmentName
        }
        return isBanned
    }

    private fun isActivityPaused(): Boolean {
        return _activity == null
    }
}