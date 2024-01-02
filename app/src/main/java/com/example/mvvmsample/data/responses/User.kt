package com.example.mvvmsample.data.responses

import androidx.room.Entity
import androidx.room.PrimaryKey

// this data class is for saving the user.
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = false)
    val mobileNumber: String,
    val password: String,
    val name: String,
    val age: Int
)