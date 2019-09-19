package com.zhuravlevmikhail.a65appshomeproject.common.interfaces

interface MyCallback<T : Any> {
    fun onResult(receivedObject : T)
    fun onError(error : String)
}