package com.zhuravlevmikhail.a65appshomeproject.fragments.mapGeneral

import com.zhuravlevmikhail.a65appshomeproject.core.baseMap.BaseMapView
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface GenMapView :
    MvpView,
    BaseMapView {

}