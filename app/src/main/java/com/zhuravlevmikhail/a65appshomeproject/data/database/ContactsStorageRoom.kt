package com.zhuravlevmikhail.a65appshomeproject.data.database

import io.reactivex.Completable
import io.reactivex.Single

class ContactsStorageRoom(private val contactDAO: ContactDAO) : ContactsStorage {
    override fun getAllContacts(): Single<List<ContactsDBEntity>> {
        return Single.create { emitter ->
            try {
                val contacts = contactDAO.getAll()
                emitter.onSuccess(contacts)
            } catch (t : Throwable) {
                emitter.onError(t)
            }
        }
    }

    override fun getContactById(id: Long): Single<ContactsDBEntity> {
        return Single.create {emitter ->
            try {
                val contact = contactDAO.getContactInfoById(id)
                emitter.onSuccess(contact)
            } catch (t : Throwable) {
                emitter.onError(t)
            }
        }
    }

    override fun addContact(contactsDBEntity: ContactsDBEntity): Completable {
        return Completable.fromAction { contactDAO.insertContact(contactsDBEntity)}
    }

    override fun updateContact(contactsDBEntity: ContactsDBEntity): Completable {
        return Completable.fromAction { contactDAO.updateContact(contactsDBEntity) }
    }
}