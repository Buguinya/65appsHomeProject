package com.zhuravlevmikhail.a65appshomeproject.stubs

import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapInteractor
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapUseCase

class MapInteractorStub : MapInteractor by MapUseCase(MapRepositoryStub())