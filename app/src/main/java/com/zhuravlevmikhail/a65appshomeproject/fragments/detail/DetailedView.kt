package com.zhuravlevmikhail.a65appshomeproject.fragments.detail

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface DetailedView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class) fun onReceivedContact(contact: ContactDetailed)
    fun showError(error : String)
    fun showError(error: Int)
    fun requestContactsPermission()
}