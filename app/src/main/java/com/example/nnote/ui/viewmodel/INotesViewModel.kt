package com.example.nnote.ui.viewmodel

import com.example.nnote.data.Note
import kotlinx.coroutines.flow.StateFlow

interface INotesViewModel {

    val notes: StateFlow<List<Note>>

     fun createNote(title: String, content: String)
     fun removeNote(note: Note)
     fun upDateNote(note: Note)
    suspend fun getNoteById(noteId: Long): Note?
}