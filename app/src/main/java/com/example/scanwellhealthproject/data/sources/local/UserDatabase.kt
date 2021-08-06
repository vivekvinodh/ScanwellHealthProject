package com.example.scanwellhealthproject.data.sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.scanwellhealthproject.util.BadgeCountConverter
import com.example.scanwellhealthproject.models.User

/**
 * Database
 */
@Database(entities = [User::class], version = 1)
@TypeConverters(BadgeCountConverter::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}