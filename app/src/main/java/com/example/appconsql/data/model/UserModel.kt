package com.example.appconsql.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "user")

data class UserModel(
    @PrimaryKey(autoGenerate =true)
    val uid:Int?=null,

    @ColumnInfo(name = "email")
    val email:String?,

    @ColumnInfo(name = "password")
    val password:String?,

    @ColumnInfo(name = "names")
    val names:String?,

    @ColumnInfo(name = "celphone")
    val celphone:String?
)
