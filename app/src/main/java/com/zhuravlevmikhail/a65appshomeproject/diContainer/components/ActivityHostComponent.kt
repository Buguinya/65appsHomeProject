package com.zhuravlevmikhail.a65appshomeproject.diContainer.components

import com.zhuravlevmikhail.a65appshomeproject.core.AppHostActivity
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.MainModule
import com.zhuravlevmikhail.a65appshomeproject.diContainer.scopes.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [MainModule::class]
)
interface ActivityHostComponent {
    fun inject(activity: AppHostActivity)
}