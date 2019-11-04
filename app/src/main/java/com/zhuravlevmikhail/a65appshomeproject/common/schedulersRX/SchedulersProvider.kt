package com.zhuravlevmikhail.a65appshomeproject.common.schedulersRX

import io.reactivex.Scheduler

interface SchedulersProvider {
    fun io() : Scheduler
    fun ui() : Scheduler
}