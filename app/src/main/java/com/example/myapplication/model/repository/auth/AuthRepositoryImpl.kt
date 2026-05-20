package com.example.myapplication.model.repository.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun register(email: String, password: String): FirebaseUser? {
        return firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .await()
            .user
    }

    override suspend fun login(email: String, password: String): FirebaseUser? {
        return firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .await()
            .user
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override fun getCurrentUserId(): String? {
        return firebaseAuth.currentUser?.uid
    }

    override suspend fun signInWithGoogle(idToken: String): FirebaseUser? {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        return firebaseAuth
            .signInWithCredential(credential)
            .await()
            .user
    }
}
