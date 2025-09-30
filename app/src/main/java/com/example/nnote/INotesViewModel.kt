package com.example.nnote

import kotlinx.coroutines.flow.StateFlow


/*
* Imagina que hoy guardas las notas en la memoria del móvil. Mañana,
* decides guardarlas en una base de datos en la nube (Firebase).
* Si has usado interfaces, solo tienes que crear una nueva clase
* FirebaseRepository que implemente la misma interfaz. No tendrías que
* cambiar ni una sola línea de código en tu ViewModel o tu View. Simplemente
* "enchufas" el nuevo repositorio.
Esto es el Principio de Inversión de Dependencias (la 'D' de SOLID) en acción.
* */
interface INotesViewModel {

    val notes: StateFlow<List<Note>>

     fun createNote(title: String, content: String)
     fun removeNote(note: Note)
     fun upDateNote(note: Note)
    suspend fun getNoteById(noteId: Long): Note?
}