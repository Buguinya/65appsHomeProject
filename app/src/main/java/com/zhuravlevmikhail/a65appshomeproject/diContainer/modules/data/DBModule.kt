package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.data

import android.content.Context
import androidx.room.Room
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst.DB_NAME
import com.zhuravlevmikhail.a65appshomeproject.data.database.AppDB
import com.zhuravlevmikhail.a65appshomeproject.data.database.ContactDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {

    @Singleton
    @Provides
    fun provideDB(context : Context) : AppDB {
        return Room.databaseBuilder(
            context,
            AppDB::class.java,
            DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideContactsDAO(appDB: AppDB) : ContactDAO {
        return appDB.getContactsDao()
    }
}