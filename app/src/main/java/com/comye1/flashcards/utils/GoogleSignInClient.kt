package com.comye1.flashcards.utils

import android.content.Context
import android.provider.Settings.Global.getString
import android.util.Log
import com.comye1.flashcards.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

fun getGoogleSignInClient(context: Context): GoogleSignInClient {
    val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestIdToken(context.getString(R.string.web_client_id))
        .build()

    Log.d("client", R.string.web_client_id.toString())
    return GoogleSignIn.getClient(context, signInOptions)
}