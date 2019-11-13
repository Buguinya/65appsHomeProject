package com.zhuravlevmikhail.a65appshomeproject.common.schedulersRX

import io.reactivex.schedulers.Schedulers

class TrampolineSchedulers : SchedulersProvider {
    override fun io() = Schedulers.trampoline()
    override fun ui() = Schedulers.trampoline()
}