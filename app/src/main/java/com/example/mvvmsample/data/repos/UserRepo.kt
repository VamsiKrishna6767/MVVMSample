package com.example.mvvmsample.data.repos

import com.example.mvvmsample.data.AppDataBase
import com.example.mvvmsample.data.responses.User
import javax.inject.Inject

// this class is for to save and retrieve the data either from DB or api calls.
class UserRepo @Inject constructor(private val db: AppDataBase) {

    // this suspend fun is for save the registered user into DB.
    suspend fun saveUser(user: User) {
        return db.getUserDao().insertOrUpdateUser(user)
    }

    // this suspend fun is for get the logged in user from DB.
    suspend fun getUser(mobile: String): User? {
        return db.getUserDao().getUser(mobile)
    }

    // this suspend fun is for get all the users who are registered user from DB.
    suspend fun getAllUsers(): List<User>? {
        return db.getUserDao().getUsers()
    }
}