package com.example.anitasks.screens.login

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {


    fun processGoogleSignInResult(intent: Intent) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
        try {
            // Отримати обліковий запис GoogleSignInAccount з інтента
            val account = task.getResult(ApiException::class.java)

            // Отримати інформацію про користувача з облікового запису
            val userId = account?.id
            val email = account?.email
            val displayName = account?.displayName
            val photoUrl = account?.photoUrl?.toString()

            // Тепер ви можете використовувати ці дані згідно з вашими потребами
            // Наприклад, передайте їх в метод для обробки входу або збережіть їх в сховищі даних
            handleUserData(userId, email, displayName, photoUrl)
        } catch (e: ApiException) {
            // Обробити помилки, які виникають під час входу за допомогою Google
            Log.d("GoogleSignIn", "signInResult:failed code=" + e.statusCode)
        }
    }

    private fun handleUserData(
        userId: String?,
        email: String?,
        displayName: String?,
        photoUrl: String?
    ) {
       Log.d("MyTag","id = $userId\nemail = $email")
    }

}
