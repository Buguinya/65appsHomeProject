package com.zhuravlevmikhail.a65appshomeproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst.DB_VERSION

@Database(entities = [ContactsDBEntity::class], version = DB_VERSION)
abstract class AppDB : RoomDatabase() {
    abstract fun getContactsDao() : ContactDAO
}