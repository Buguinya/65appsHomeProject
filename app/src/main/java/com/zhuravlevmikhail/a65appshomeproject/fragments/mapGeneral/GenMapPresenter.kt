package com.zhuravlevmikhail.a65appshomeproject.fragments.mapGeneral

import com.zhuravlevmikhail.a65appshomeproject.core.baseMap.BaseMapPresenter
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapInteractor
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject


@InjectViewState
class GenMapPresenter
    @Inject constructor(
        private val mapInteractor: MapInteractor,
        private val apiKey: String):
    MvpPresenter<GenMapView>(),
    BaseMapPresenter{

    override fun onMapCreated() {

    }
}