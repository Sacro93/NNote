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

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.NoteListScreen.route
    )
    {
        composable(Screen.NoteListScreen.route) {
            NotesListScreen(navController = navController)
        }

        composable(
            route = Screen.AddEditNoteScreen.route,
            arguments = listOf(navArgument("noteId") { type = NavType.LongType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getLong("noteId") ?: -1L

            AddEditNoteScreen(noteId = noteId, navController = navController)
        }
    }
}