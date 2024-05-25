package com.example.notesapp.room

import androidx.lifecycle.LiveData
import com.example.notesapp.room.Note
import com.example.notesapp.room.NoteDao

class NoteRepository(private val notesDao: NoteDao) {
    val allNotes: LiveData<MutableList<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    suspend fun delete(note: Note) {
        notesDao.delete(note)
    }

    suspend fun update(note: Note) {
        notesDao.update(note)
    }
}