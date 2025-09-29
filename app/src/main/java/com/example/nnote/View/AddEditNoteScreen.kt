package com.example.nnote.View

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nnote.Note
import com.example.nnote.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    noteId : Long,
    viewModel: NotesViewModel,
    navController: NavController
) {

    //para recordar el texto que el usuario escribe
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    //se cargar los datos de la nota que se va a editar
    //Este es el cerebro de la pantalla
    LaunchedEffect(key1 = Unit) {
        // Si el noteId NO es -1, significa que estamos editando una nota existente.
        if (noteId != -1L) {
            val existingNote = viewModel.getNoteById(noteId)
            if (existingNote != null) {
                // Rellenamos los campos de texto con los datos de la nota.
                title = existingNote.title
                content = existingNote.content
            }
        }
    }

    // 3. Screen Structure
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (noteId == -1L) "Create Note" else "Edit Note") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        // 4. Form
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            // 5. Save Button
            Button(
                onClick = {
                    if (title.isNotBlank() && content.isNotBlank()) {
                        if (noteId == -1L) {
                            viewModel.createNote(title, content)
                        } else {
                            val updatedNote = Note(id = noteId, title = title, content = content)
                            viewModel.upDateNote(updatedNote)
                        }
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Note")
            }
        }
    }
}