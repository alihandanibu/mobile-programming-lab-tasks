package com.example.myapplication.model.repository.user

import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun saveUserId(userId: Int)
    fun getUserId(): Flow<Int?>
    suspend fun clearSession()
}
