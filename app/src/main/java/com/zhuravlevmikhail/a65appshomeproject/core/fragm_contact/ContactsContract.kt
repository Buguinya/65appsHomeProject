package com.zhuravlevmikhail.a65appshomeproject.core.fragm_contact

import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpModel
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpPresenter
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpView

interface ContactsContract {

    interface TModel : MvpModel

    interface TPresenter<V : TView> : MvpPresenter<V>

    interface TView : MvpView
}