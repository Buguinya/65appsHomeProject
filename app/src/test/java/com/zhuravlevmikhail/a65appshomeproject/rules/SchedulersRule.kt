package com.zhuravlevmikhail.a65appshomeproject.rules

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class SchedulersRule : TestRule {

    private val testScheduler = TestScheduler()

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                RxJavaPlugins.setInitIoSchedulerHandler { testScheduler }
                RxJavaPlugins.setInitComputationSchedulerHandler { testScheduler }
                RxJavaPlugins.setInitNewThreadSchedulerHandler { testScheduler }
                RxJavaPlugins.setInitSingleSchedulerHandler { testScheduler }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { testScheduler }
                try {
                    base?.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxJavaPlugins.reset()
                }
            }
        }
    }
}