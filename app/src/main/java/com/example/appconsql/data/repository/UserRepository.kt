package com.example.appconsql.data.repository

import android.content.Context
import com.example.appconsql.data.db.AppDatabase
import com.example.appconsql.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(context: Context) {

    private val database = AppDatabase.getIntance(context)

    suspend fun validatelogin(email: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val userFromDb = database.userDao().getUserByCredentials(email, password)
            userFromDb != null
        }
    }

    suspend fun getUsers(): List<UserModel> {
        return withContext(Dispatchers.IO) {
            database.userDao().getUsers()
        }
    }

    suspend fun getUserById(uid: Long): UserModel {
        return withContext(Dispatchers.IO) {
            database.userDao().getUserById(uid)


        }
    }

    suspend fun updateUser(
        email: String, password: String, names: String, celphone: String, uid: Long
    ):Int {
        return withContext(Dispatchers.IO) {
            database.userDao().update(
                email, password, names, celphone, uid)
        }
    }


    suspend fun insertUser(user: UserModel): Boolean {
        return withContext(Dispatchers.IO) {
            val userId= database.userDao().insert(user)
            userId.toInt() != 0

        }
    }


}