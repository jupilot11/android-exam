package com.joeydee.android_exam.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joeydee.android_exam.objects.UserResult

@Dao
interface RandomUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user: List<UserResult>)

    @Query("SELECT * FROM user_table")
    fun getAll(): List<UserResult>

    @Query("SELECT * FROM user_table WHERE primaryKey = :id")
    suspend fun getPerson(id: String): UserResult

}