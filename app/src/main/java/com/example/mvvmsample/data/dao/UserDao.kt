package com.example.mvvmsample.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmsample.data.responses.User

// this DAO interface is for insert the user into DB or get the user from DB by using queries.
@Dao
interface UserDao {
    // this is for inserting user.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(user: User)

    // this is for get the user by mobile number.
    @Query("SELECT * FROM user_table WHERE user_table.mobileNumber LIKE :mobile")
    suspend fun getUser(mobile: String): User?

    // thi is for get all the users from DB.
    @Query("SELECT * FROM user_table")
    suspend fun getUsers(): List<User>?
}