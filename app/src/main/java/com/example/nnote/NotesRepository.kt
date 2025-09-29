package com.example.nnote

class NotesRepository {

    private val notesInMemory = mutableListOf<Note>()
    private var currentId = 0;
    fun getNotes(): List<Note> {
        return notesInMemory.toList()
    }

    // Devolvemos una copia de la lista para que no se pueda modificar desde fuera.
    // Es una capa extra de seguridad (encapsulación).
    fun addNote(title: String, content: String) {
        val newNote = Note(
            id = currentId++.toLong(),
            title = title,
            content = content
        )
        notesInMemory.add(newNote)
    }

    fun removeNote(note: Note) {
        notesInMemory.remove(note)
    }


    fun upDateNote(updatedNote: Note) {
        val index = notesInMemory.indexOfFirst { it.id == updatedNote.id }
        if (index != -1) {
            notesInMemory[index] = updatedNote
        }
    }

    fun getNoteById(id: Long): Note? {
        val index = notesInMemory.indexOfFirst { it.id == id }
        return if (index != -1) {
            notesInMemory[index]
        } else {
            null
        }
    }


}