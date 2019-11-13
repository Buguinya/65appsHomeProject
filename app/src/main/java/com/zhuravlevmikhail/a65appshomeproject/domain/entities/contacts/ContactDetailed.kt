package com.zhuravlevmikhail.a65appshomeproject.domain.entities.contacts

import android.net.Uri

data class ContactDetailed (
    val name : String,
    val phone: String,
    var email : String?,
    val image : Uri? = null
)