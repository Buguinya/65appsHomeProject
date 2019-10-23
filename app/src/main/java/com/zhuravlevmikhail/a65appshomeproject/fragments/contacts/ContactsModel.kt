package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts

const val FRAGMENT_DATA_KEY_SAVED_QUERY = "FRAGMENT_DATA_KEY_SAVED_QUERY"

data class ContactGeneral (
    val id : Long,
    val name : String,
    val phone : String
)
