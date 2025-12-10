package com.example.cuidapet.data.repository

import com.example.cuidapet.data.local.dao.UserDao
import com.example.cuidapet.data.local.entities.toEntity
import com.example.cuidapet.data.local.entities.toExternalModel
import com.example.cuidapet.ui.screens.user.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(private val userDao: UserDao) {

    suspend fun saveUser(user: User) {
        userDao.insertUser(user.toEntity())
    }

    fun getUser(): Flow<User?> {
        return userDao.getUser().map { entity ->
            entity?.toExternalModel()
        }
    }

    suspend fun clearUser() {
        userDao.clearUser()
    }
}