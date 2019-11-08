package com.zhuravlevmikhail.a65appshomeproject.data.database

import com.zhuravlevmikhail.a65appshomeproject.domain.entities.map.ContactOnMapEntity
import io.reactivex.Completable
import io.reactivex.Single

interface ContactsStorage {
    fun getAllContacts() : Single<List<ContactsDBEntity>>
    fun getContactById(id : Long) : Single<ContactOnMapEntity>
    fun addContact(contactsDBEntity: ContactsDBEntity) : Completable
    fun updateContact(contactsDBEntity: ContactsDBEntity) : Completable
}