package com.zhuravlevmikhail.a65appshomeproject.core.contacts

class ContactsModel : ContactsContract.ContactsModelContract {

    data class ContactGeneral (
        val id : Long,
        val name : String,
        val phone : String
    )
}