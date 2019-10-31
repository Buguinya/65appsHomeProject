package com.zhuravlevmikhail.a65appshomeproject.data.database

import androidx.room.*

@Dao
interface ContactDAO {

    @Query("SELECT * FROM contacts")
    fun getAll(): List<ContactsDBEntity>

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getContactInfoById(id: Long?): ContactsDBEntity

    @Update
    fun updateContact(contactsDBEntity: ContactsDBEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contactsDBEntity: ContactsDBEntity)
}