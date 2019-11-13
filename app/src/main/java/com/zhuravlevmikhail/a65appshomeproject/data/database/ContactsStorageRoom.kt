package com.zhuravlevmikhail.a65appshomeproject.data.database

import com.zhuravlevmikhail.a65appshomeproject.domain.entities.map.ContactOnMapEntity
import io.reactivex.Completable
import io.reactivex.Single

class ContactsStorageRoom(private val contactDAO: ContactDAO) : ContactsStorage {
    override fun getAllContacts(): Single<List<ContactsDBEntity>> {
        return Single.create { emitter ->
            if (emitter.isDisposed) { return@create }
            try {
                val contacts = contactDAO.getAll()
                emitter.onSuccess(contacts)
            } catch (t : Throwable) {
                emitter.onError(t)
            }
        }
    }

    override fun getContactById(id: Long): Single<ContactOnMapEntity> {
        return Single.create {emitter ->
            try {
                if (!emitter.isDisposed) {
                    val contact = contactDAO.getContactInfoById(id)
                    with(contact) {
                        val domainEntity =
                            ContactOnMapEntity(
                                id, address, longtude, latitude
                            )
                        emitter.onSuccess(domainEntity)
                    }
                }
            } catch (t : Throwable) {
                if (!emitter.isDisposed) emitter.onError(t)
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