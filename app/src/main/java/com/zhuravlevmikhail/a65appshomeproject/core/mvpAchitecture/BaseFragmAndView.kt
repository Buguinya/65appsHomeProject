package com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture

import androidx.fragment.app.Fragment

abstract class BaseFragmAndView<Model: MvpModel, MyView: MvpView, Presenter: MvpPresenter<MyView>>:
    Fragment(),
    MvpView {
}