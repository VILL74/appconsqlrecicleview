package com.example.appconsql.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.appconsql.data.model.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun getUserByCredentials(email:String,password:String):UserModel?

    @Query("select * from user where uid=:uid")
    fun getUserById(uid:Long):UserModel

    @Query("SELECT * FROM user")
    fun getUsers():List<UserModel>

    @Insert
    fun insert(user: UserModel):Long

    @Delete
    fun delete(user:UserModel)

    @Query("update user set email=:email ,password=:password,names=:names,celphone=:celphone where uid=:uid")

    fun update(email:String,password:String,names:String,celphone:String,uid: Long):Int

}