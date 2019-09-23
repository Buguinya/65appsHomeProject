package com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zhuravlevmikhail.a65appshomeproject.appManagers.PageManager
import com.zhuravlevmikhail.a65appshomeproject.core.App
import java.util.*

const val BUNDLE_KEY_LAYOUT_ID = "BUNDLE_KEY_LAYOUT_ID"
abstract class BaseFragmAndView<Model: MvpModel, MyView: MvpView, Presenter: MvpPresenter<MyView>>:
    Fragment(),
    MvpView {

    protected lateinit var _mvpPresenter: Presenter

    private var layoutId: Int = 0
    protected var isPrevPageNeedUpdate = false
    protected lateinit var pageManager: PageManager


    override fun configure(layoutId: Int, pageManager: PageManager, fragmentData: Bundle?) {
        this.layoutId = layoutId
        this.pageManager = pageManager
    }

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            restoreBundle(it)
        }
        pageManager = App.instance.pageManager
        firstInit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle? ) {
        super.onViewCreated(view, savedInstanceState)
        lightInitViews()
        _mvpPresenter.lightInitViews()
        this.initAfterPageAnim()
    }

    private fun initAfterPageAnim() {
        view?.let {
            loadData()
            _mvpPresenter.loadData()
        }
    }

    override fun lightInitViews() {
        /* LOADING LIGHT INFO FOR VIEWS BEFORE ANIMATION*/
    }

    override fun loadData() {
        /* LOADING INFO AFTER ANIMATION */
    }

    override fun showSnackbar(message: String) {
        pageManager.showSnackBar(message)
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
        _mvpPresenter.destroy()
    }

    override fun onDestroy() {
        freeView()
        super.onDestroy()
    }
}