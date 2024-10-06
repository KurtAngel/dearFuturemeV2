package com.example.dearfutureme

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_acc")
data class User(
    @PrimaryKey(autoGenerate = true) val pId : Int,
    @ColumnInfo("Username")val username : String,
    @ColumnInfo("Password")val password : String
)
