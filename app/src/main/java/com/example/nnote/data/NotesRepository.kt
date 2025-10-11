package com.example.nnote.data

import kotlinx.coroutines.flow.Flow

class NotesRepository ( private val noteDao: NoteDao){
     fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

   suspend fun addNote(note: Note) {
       noteDao.insertNote(note)
    }

    suspend fun removeNote(note: Note) {
        noteDao.deleteNote(note)
    }


    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    suspend fun getNoteById(id: Long): Note? {
        return noteDao.getNotesById(id)
    }


}