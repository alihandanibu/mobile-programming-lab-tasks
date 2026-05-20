package com.example.myapplication.model.repository.user

import com.example.myapplication.model.data.local.dao.UserDao
import com.example.myapplication.model.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun insertUser(user: UserEntity): Long = userDao.insertUser(user)
    override suspend fun getUserByEmailAndPassword(email: String, password: String): UserEntity? = 
        userDao.getUserByEmailAndPassword(email, password)
    override suspend fun getUserByEmail(email: String): UserEntity? = 
        userDao.getUserByEmail(email)
    override suspend fun getUserById(id: Int): UserEntity? = 
        userDao.getUserById(id)
    override fun observeUserById(id: Int): Flow<UserEntity?> = 
        userDao.observeUserById(id)
    override suspend fun updateUser(user: UserEntity) = 
        userDao.updateUser(user)
    override suspend fun deleteUser(user: UserEntity) = 
        userDao.deleteUser(user)
}
