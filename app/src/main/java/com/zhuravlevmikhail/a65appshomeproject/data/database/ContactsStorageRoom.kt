package com.zhuravlevmikhail.a65appshomeproject.data.database

import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.ContactOnMapDomainEntity
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

    override fun getContactById(id: Long): Single<ContactOnMapDomainEntity> {
        return Single.create {emitter ->
            if (emitter.isDisposed) { return@create }
            try {
                val contact = contactDAO.getContactInfoById(id)
                with (contact) {
                    val domainEntity =
                        ContactOnMapDomainEntity(
                            id, address, longtude, latitude
                        )
                    emitter.onSuccess(domainEntity)
                }
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