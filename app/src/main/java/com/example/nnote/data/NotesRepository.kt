package com.example.nnote.data

import kotlinx.coroutines.flow.Flow

class NotesRepository ( private val noteDao: NoteDao){
     fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

   /*Las funciones del Repository son suspend, porque escribir en la base de datos del
   móvil es una operación "lenta" que no puede hacerse en el hilo principal (bloquearía la pantalla).
    Para llamar a una función suspend, es obligatorio hacerlo dentro de una corrutina.
   .launch es la forma estándar de crear una corrutina segura en el ViewModel.*/
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