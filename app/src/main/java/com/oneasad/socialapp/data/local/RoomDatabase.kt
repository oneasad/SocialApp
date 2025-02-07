package com.oneasad.socialapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalPost::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}
