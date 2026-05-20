package com.example.myapplication.model.repository.user

import com.example.myapplication.model.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: UserEntity): Long
    suspend fun getUserByEmailAndPassword(email: String, password: String): UserEntity?
    suspend fun getUserByEmail(email: String): UserEntity?
    suspend fun getUserById(id: Int): UserEntity?
    fun observeUserById(id: Int): Flow<UserEntity?>
    suspend fun updateUser(user: UserEntity)
    suspend fun deleteUser(user: UserEntity)
}
