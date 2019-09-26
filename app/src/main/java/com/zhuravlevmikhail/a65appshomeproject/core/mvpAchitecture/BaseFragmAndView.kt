package com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

const val BUNDLE_KEY_LAYOUT_ID = "BUNDLE_KEY_LAYOUT_ID"
abstract class BaseFragmAndView<MyView: MvpView, Presenter: MvpPresenter<MyView>>:
    Fragment(),
    MvpView {

    protected lateinit var mvpPresenter: Presenter

    private var layoutId: Int = 0


    override fun configure(layoutId: Int) {
        this.layoutId = layoutId
    }

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            restoreBundle(it)
        }
        firstInit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle? ) {
        super.onViewCreated(view, savedInstanceState)
        lightInitViews()
        mvpPresenter.lightInitViews()
        this.initAfterPageAnim()
    }

    private fun initAfterPageAnim() {
        view?.let {
            loadData()
            mvpPresenter.loadData()
        }
    }

    override fun lightInitViews() {
        /* LOADING LIGHT INFO FOR VIEWS BEFORE ANIMATION*/
    }

    override fun loadData() {
        /* LOADING INFO AFTER ANIMATION */
    }

    override fun onSaveInstanceState(outState: Bundle) {
        saveInBundle(outState)
        super.onSaveInstanceState(outState)
    }

    override fun saveInBundle(bundle: Bundle) {
        bundle.putInt(BUNDLE_KEY_LAYOUT_ID, layoutId)
    }

    override fun restoreBundle(bundle: Bundle) {
        layoutId = bundle.getInt(BUNDLE_KEY_LAYOUT_ID)
    }

    override fun freeView() {
        mvpPresenter.destroy()
    }

    override fun onDestroy() {
        freeView()
        super.onDestroy()
    }

    override fun showError(error: Int) {
        val text = getString(error)
        getToastShort(text).show()
    }

    override fun showError(error: String) {
        getToastShort(error).show()
    }

    private fun getToastShort(message: String): Toast {
        return Toast.makeText(context, message, Toast.LENGTH_SHORT)
    }
}