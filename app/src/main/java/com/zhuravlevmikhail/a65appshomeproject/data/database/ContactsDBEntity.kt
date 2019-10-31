package com.zhuravlevmikhail.a65appshomeproject.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contacts")
class ContactsDBEntity(
    @PrimaryKey(autoGenerate = false) val id : Long,
    val address : String,
    val longtude : String,
    val latitude : String
)