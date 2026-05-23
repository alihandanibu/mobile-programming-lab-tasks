package com.example.myapplication.presentation.util

import android.content.Context
import android.content.Intent
import com.example.myapplication.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import javax.inject.Inject

class GoogleAuthClient @Inject constructor() {

    fun getSignInClient(context: Context): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso)
    }

    fun getSignInIntent(context: Context): Intent {
        return getSignInClient(context).signInIntent
    }
}
