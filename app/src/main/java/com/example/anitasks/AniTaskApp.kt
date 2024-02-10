package com.example.anitasks

import androidx.multidex.MultiDexApplication
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AniTaskApp: MultiDexApplication(){
    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        super.onCreate()
    }
}