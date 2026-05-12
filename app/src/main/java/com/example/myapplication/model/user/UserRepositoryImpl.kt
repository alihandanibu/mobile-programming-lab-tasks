package com.example.myapplication.model.repository.user

import com.example.myapplication.model.data.local.dao.UserDao
import com.example.myapplication.model.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
): UserRepository{
    override suspend fun insertUser(user: UserEntity): Long{
        return userDao.insertUser(user)
    }
    
    override suspend fun login(email: String, password: String): UserEntity?{
        return userDao.login(email, password)
    }

    override fun observeUser(id: Int): Flow<UserEntity?> {
        return userDao.observeUser(id)
    }

    override suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }

    override suspend fun deleteUser(user: UserEntity) {
        userDao.deleteUser(user)
    }
}