package com.example.anitasks.features.subjects.repository

import com.example.anitasks.core.data.local.room.dao.SubjectDao
import com.example.anitasks.core.data.model.Subject

class SubjectRepository(private val dao: SubjectDao) {

    suspend fun getAllSubjects(): List<Subject> {
        return dao.getAllSubjects()
    }

    suspend fun createSubject(name: String, teacherName: String?) {
        dao.insertSubject(
            Subject(
                name = name,
                teacherName = teacherName
            )
        )
    }

    suspend fun updateSubject(id: Long, name: String, teacherName: String?) {
        dao.updateSubject(
            Subject(
                id = id,
                name = name,
                teacherName = teacherName
            )
        )
    }
    suspend fun deleteSubject(subject: Subject) {
        dao.deleteSubject(subject)
    }
    suspend fun deleteSubjectById(id: Long) {
        dao.deleteSubjectById(id)
    }
}