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

    private var _layoutId: Int = 0
    private var _fragmentData: HashMap<String, Any>? = null
    protected var _isPrevPageNeedUpdate = false
    protected lateinit var _pageManager: PageManager


    override fun configure(layoutId: Int, pageManager: PageManager, fragmentData: HashMap<String, Any>?) {
        _layoutId = layoutId
        _pageManager = pageManager
        _fragmentData = fragmentData
    }

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            restoreBundle(it)
        }
        _pageManager = App.instance.pageManager
        firstInit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(_layoutId, container, false)
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

    override fun getFragmentData(): Map<String, Any>? {
        _fragmentData?.let {
            return Collections.unmodifiableMap(it)
        }
        return null
    }

    override fun showSnackbar(message: String) {
        _pageManager.showSnackBar(message)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        saveInBundle(outState)
        super.onSaveInstanceState(outState)
    }

    override fun saveInBundle(bundle: Bundle) {
        bundle.putInt(BUNDLE_KEY_LAYOUT_ID, _layoutId)
    }

    override fun restoreBundle(bundle: Bundle) {
        _layoutId = bundle.getInt(BUNDLE_KEY_LAYOUT_ID)
    }

    override fun freeView() {
        _mvpPresenter.destroy()
    }

    override fun onDestroy() {
        freeView()
        super.onDestroy()
    }
}