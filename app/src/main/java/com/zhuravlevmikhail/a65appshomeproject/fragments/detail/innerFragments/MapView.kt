package com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments

import com.zhuravlevmikhail.a65appshomeproject.core.baseMap.BaseMapView
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface MapView :
    MvpView,
    BaseMapView