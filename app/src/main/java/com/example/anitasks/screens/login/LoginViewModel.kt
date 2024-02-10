package com.example.anitasks.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.anitasks.core.features.user.repository.UserRepository
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
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

    fun processGoogleSignInResult(googleCredential: SignInCredential) {
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val idToken = googleCredential.googleIdToken
        when {
            idToken != null -> {
                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                auth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            saveData(user)
                            _loginResult.value = false
                        } else {
                            Log.d("GoogleSignIn", "signInResult:failed")
                            _loginResult.value = false
                        }
                    }
            }

            else -> {
                // Shouldn't happen.
                Log.d("MyLog", "No ID token!")
            }
        }
    }

    private fun saveData(user: FirebaseUser?) {
        repository.saveUserData(
            userId = user?.uid,
            email = user?.email,
            name = user?.displayName,
            photoUrl = user?.photoUrl?.toString()
        )
    }

}
