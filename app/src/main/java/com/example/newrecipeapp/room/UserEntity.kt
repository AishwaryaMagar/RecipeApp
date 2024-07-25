package com.example.newrecipeapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user-table")
class UserEntity(

    @PrimaryKey
    var username: String,
    var password: String,
)