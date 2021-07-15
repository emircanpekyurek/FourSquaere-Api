package com.pekyurek.emircan.data.db.dao

import androidx.room.*
import com.pekyurek.emircan.data.db.model.UserInfo

@Dao
interface UserInfoDao {

    @Transaction
    @Query("DELETE FROM userinfo")
    fun deleteLoggedInUser()

    @Query("SELECT * FROM userinfo")
    fun getLoggedInUser(): UserInfo?

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userInfo: UserInfo)

}