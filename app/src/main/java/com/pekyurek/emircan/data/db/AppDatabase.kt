package com.pekyurek.emircan.data.db

import androidx.annotation.Keep
import androidx.room.Database
import androidx.room.RoomDatabase
import com.pekyurek.emircan.data.db.dao.UserInfoDao
import com.pekyurek.emircan.data.db.model.UserInfo

@Database(entities = [UserInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userInfoDao(): UserInfoDao

    @Keep
    companion object {
        val DATABASE_NAME = AppDatabase::class.java.simpleName
    }
}