package com.joeydee.android_exam.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.joeydee.android_exam.objects.UserResult
import com.joeydee.android_exam.utils.Converters

@Database(entities = [UserResult::class], version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class RandomUserDb : RoomDatabase() {

    abstract val randomUserDao: RandomUserDao

    companion object {
        @Volatile
        private var INSTANCE: RandomUserDb? = null
        fun getInstance(context: Context): RandomUserDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RandomUserDb::class.java,
                    "room"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}