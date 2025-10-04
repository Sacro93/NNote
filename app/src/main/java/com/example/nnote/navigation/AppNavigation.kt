package com.example.nnote.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nnote.ui.screens.AddEditNoteScreen
import com.example.nnote.ui.screens.NotesListScreen


@Composable
fun AppNavigation() {

    // 1. Creamos el "maquinista" (NavController).
    // rememberNavController() lo crea y lo recuerda durante el ciclo de vida de la app.

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.NoteListScreen.route
    )
    {
        composable(Screen.NoteListScreen.route) {
            NotesListScreen(navController = navController)
        }

        // Estación B: El editor de notas
        composable(
            // La ruta incluye un "argumento" o "parámetro" entre llaves.
            route = Screen.AddEditNoteScreen.route,
            arguments = listOf(navArgument("noteId") { type = NavType.LongType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getLong("noteId") ?: -1L

            AddEditNoteScreen(noteId = noteId, navController = navController)
        }
    }
}