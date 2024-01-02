package com.example.mvvmsample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmsample.data.dao.UserDao
import com.example.mvvmsample.data.responses.User

// this abstract class for creating data base and accessing dao.
@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        private var INSTANCE: AppDataBase? = null
        operator fun invoke(context: Context): AppDataBase = INSTANCE ?: synchronized(this) {
            return@synchronized Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "app_db"
            ).build()
        }.also { INSTANCE = it }
    }
}