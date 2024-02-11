package com.example.anitasks.features.exams.di

import com.example.anitasks.core.data.local.room.db.AnitaskAppDatabase
import com.example.anitasks.features.exams.repository.ExamRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExamModule {

    @Provides
    @Singleton
    fun provideExamRepository(db:AnitaskAppDatabase): ExamRepository {
        return ExamRepository(db.examDao,db.subjectDao)
    }
}