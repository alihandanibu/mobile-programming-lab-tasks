package com.example.myapplication.model.repository.auth

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun register(email: String, password: String): FirebaseUser?
    suspend fun login(email: String, password: String): FirebaseUser?
    fun logout()
    fun getCurrentUserId(): String?
    suspend fun signInWithGoogle(idToken: String): FirebaseUser?
}
