package com.example.nnote.navigation

sealed class Screen (val route : String) {

    object NoteListScreen : Screen("note_list_screen")
    object AddEditNoteScreen : Screen("add_edit_note_screen/{noteId}")

    fun withArgs(noteId: Long): String {
        return route.replace("{noteId}", noteId.toString())
    }
}