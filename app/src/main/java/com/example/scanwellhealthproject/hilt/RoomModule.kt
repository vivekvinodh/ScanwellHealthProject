package com.example.scanwellhealthproject.hilt

import android.content.Context
import androidx.room.Room
import com.example.scanwellhealthproject.data.sources.local.UserDao
import com.example.scanwellhealthproject.data.sources.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Module providing database and DAO
 */
@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    /**
     * Provides Room Database
     *
     * @param appContext Context
     * @return UserDatabase
     */
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext appContext: Context): UserDatabase {
        return Room.databaseBuilder(
            appContext,
            UserDatabase::class.java,
            "app.db"
        ).build()
    }

    /**
     * Provides DAO
     *
     * @param userDatabase UserDatabase
     * @return UserDao
     */
    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }
}