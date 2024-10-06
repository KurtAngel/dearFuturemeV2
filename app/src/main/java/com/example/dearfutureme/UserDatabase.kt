package com.example.dearfutureme

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [User::class])
abstract class UserDatabase : RoomDatabase(){

    abstract fun userDao() : UserDao
    companion object {

        @Volatile
        private var INSTANCE : UserDatabase? = null

        fun getDatabase(context : Context) : UserDatabase {

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    UserDatabase::class.java,
                    "UserAccount"
                    ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}