package com.example.nnote

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotesViewModel(private val notesRepository: NotesRepository) : ViewModel(), INotesViewModel {

    // Esta es la versión "editable" del flujo de datos,
    // solo el ViewModel puede modificarla.
    private val _notes = MutableStateFlow<List<Note>>(emptyList())

    // Exponemos un 'StateFlow' público e inmutable.
    // La UI se suscribirá a este. '.asStateFlow()' lo convierte en solo lectura.
    override val notes: StateFlow<List<Note>> = _notes.asStateFlow()


    init {
        // 'viewModelScope.launch' es la forma correcta de
        // iniciar corrutinas en un ViewModel.
        viewModelScope.launch {
            loadNotes()
        }
    }

    override fun loadNotes() {
        //actualizar lista local
        _notes.value = notesRepository.getNotes()
    }

    override fun createNote(title: String, content: String) {
        notesRepository.addNote(title, content)
        loadNotes()
    }

    override fun removeNote(note: Note) {
        notesRepository.removeNote(note)
        loadNotes()
    }

    //actualizar nota
    override fun upDateNote(note: Note) {
        notesRepository.upDateNote(note)
        loadNotes()

    }

    override fun getNoteById(noteId: Long): Note? {
       return notesRepository.getNoteById(noteId)
    }
}


