package com.example.anitasks.core.features.lessons.di

import com.example.anitasks.core.data.local.room.db.AnitaskAppDatabase
import com.example.anitasks.core.features.lessons.repository.LessonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LessonModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        db: AnitaskAppDatabase
    ): LessonRepository {
        return LessonRepository(db.lessonDao, db.subjectDao)
    }
}