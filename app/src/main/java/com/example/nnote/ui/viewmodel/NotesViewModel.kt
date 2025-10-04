package com.example.nnote.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nnote.data.Note
import com.example.nnote.data.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor( // <--  Hilt inyectará aquí el Repository
    private val repository: NotesRepository
) : ViewModel(), INotesViewModel {

    override val notes: StateFlow<List<Note>> = repository.getNotes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )


    override fun createNote(title: String, content: String) {
        viewModelScope.launch {
            val newNote = Note(title = title, content = content)
            repository.addNote(newNote)
        }
    }

    override fun upDateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

    override fun removeNote(note: Note) {
        viewModelScope.launch {
            repository.removeNote(note)
        }
    }

    override suspend fun getNoteById(noteId: Long): Note? {
        return repository.getNoteById(noteId)
    }
}


