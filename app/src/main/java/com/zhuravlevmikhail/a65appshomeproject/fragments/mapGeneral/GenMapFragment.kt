package com.zhuravlevmikhail.a65appshomeproject.fragments.mapGeneral


import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.core.baseMap.BaseMapFragment
import com.zhuravlevmikhail.a65appshomeproject.core.baseMap.BaseMapPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class GenMapFragment :
    BaseMapFragment(),
    GenMapView{

    override fun getOnMapClickListener() = GoogleMap.OnMapClickListener { }

    override fun getBaseMapPresenter() = genMapPresenter

    @Inject
    lateinit var presenterProvider: Provider<GenMapPresenter>

    @InjectPresenter
    lateinit var genMapPresenter: GenMapPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App
            .instance
            .appComponent
            .plucGenMapComponent()
            .inject(this@GenMapFragment)
    }

    @ProvidePresenter
    fun providePresenter() : GenMapPresenter {
        return presenterProvider.get()
    }
}