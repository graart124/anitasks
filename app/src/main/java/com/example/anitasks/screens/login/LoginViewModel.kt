package com.example.anitasks.screens.login

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.anitasks.core.features.user.repository.UserRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _loginResult = MutableStateFlow<Boolean?>(null)
    val loginResult: StateFlow<Boolean?> = _loginResult

    fun processGoogleSignInResult(intent: Intent) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
        try {
            val account = task.getResult(ApiException::class.java)

            repository.saveUserData(
                userId = account?.id,
                email = account?.email,
                name = account?.displayName,
                photoUrl = account?.photoUrl?.toString()
            )
            _loginResult.value = true
        } catch (e: ApiException) {
            Log.d("GoogleSignIn", "signInResult:failed code=" + e.statusCode)
            _loginResult.value = false
        }
    }

}
