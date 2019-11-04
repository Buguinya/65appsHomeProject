package com.zhuravlevmikhail.a65appshomeproject.common.schedulersRX

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommonSchedulers : SchedulersProvider {
    override fun io() = Schedulers.io()
    override fun ui() = AndroidSchedulers.mainThread() as Scheduler
}