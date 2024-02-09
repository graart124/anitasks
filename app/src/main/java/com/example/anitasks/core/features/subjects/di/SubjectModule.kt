package com.example.anitasks.core.features.subjects.di

import com.example.anitasks.core.data.local.room.db.AnitaskAppDatabase
import com.example.anitasks.core.features.subjects.repository.SubjectRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SubjectModule {

    @Provides
    @Singleton
    fun provideSubjectRepository(db: AnitaskAppDatabase): SubjectRepository {
        return SubjectRepository(db.subjectDao)
    }
}