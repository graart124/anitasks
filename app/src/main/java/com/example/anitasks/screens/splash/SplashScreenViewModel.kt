package com.example.anitasks.screens.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.anitasks.features.user.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    repository: UserRepository
) : ViewModel() {
    private val _isUserAuthorized = mutableStateOf(false)
    val isUserAuthorized: State<Boolean> = _isUserAuthorized

    init {
        _isUserAuthorized.value = repository.getUserData()!=null
    }

}