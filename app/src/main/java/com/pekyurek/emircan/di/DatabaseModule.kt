package com.pekyurek.emircan.di

import android.content.Context
import androidx.room.Room
import com.pekyurek.emircan.AppApplication
import com.pekyurek.emircan.data.db.AppDatabase
import com.pekyurek.emircan.data.db.dao.UserInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME).apply {
            if (context !is AppApplication) allowMainThreadQueries()
        }.build()
    }

    @Singleton
    @Provides
    fun provideUserInfoDao(appDatabase: AppDatabase): UserInfoDao {
        return appDatabase.userInfoDao()
    }

}