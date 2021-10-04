package com.example.scanwellhealthproject.data.sources.local

import androidx.room.*
import com.example.scanwellhealthproject.models.User

/**
 * Database Access Object
 */
@Dao
interface UserDao{

    /**
     * Query function to retrieve list of [User], ordered by descending reputation.
     * May be null if no Users.
     *
     * @return List<User>
     */
    @Query("SELECT * FROM user_table order by reputation DESC")
    fun getAllUsers(): List<User>

    /**
     * Query function to retrieve a specific [User] by [accountId]
     * May be null if no Users
     *
     * @param accountId Int
     * @return User
     */
    @Query("SELECT * FROM user_table where accountId = :accountId")
    fun getUser(accountId : Int) : User?

    /**
     * Insert function to insert a list of [User], replacing any conflicts
     *
     * @param movies List<User>
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<User>)

    /**
     * Delete function to delete a list of [User]
     *
     * @param user List<User>
     */
    @Delete
    fun deleteAll(user: List<User>)
}