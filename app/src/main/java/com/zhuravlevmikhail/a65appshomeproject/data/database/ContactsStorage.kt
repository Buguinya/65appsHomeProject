package com.zhuravlevmikhail.a65appshomeproject.data.database

import io.reactivex.Completable
import io.reactivex.Single

interface ContactsStorage {
    fun getAllContacts() : Single<List<ContactsDBEntity>>
    fun getContactById(id : Long) : Single<ContactsDBEntity>
    fun addContact(contactsDBEntity: ContactsDBEntity) : Completable
    fun updateContact(contactsDBEntity: ContactsDBEntity) : Completable
}