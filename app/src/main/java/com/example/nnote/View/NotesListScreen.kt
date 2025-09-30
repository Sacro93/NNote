package com.example.nnote.View

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nnote.Note
import com.example.nnote.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListScreen(
    viewModel: NotesViewModel,
    navController: NavController
) {

    // 'collectAsState()' convierte el flujo de datos (StateFlow) del ViewModel en un
    // estado que Jetpack Compose puede observar.
    // La palabra clave 'by' desenvuelve el estado, dándonos la lista de notas directamente.
    //collectAsState crea una conexión viva y automática entre la UI y los datos del ViewModel.
    val notes by viewModel.notes.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notes") },
                // La sección 'actions' es para los iconos de la derecha.
                actions = {
                    IconButton(onClick = { /* TODO: Lógica para ajustes */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                },
                // Personalizamos los colores para que se ajusten a tu diseño.
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface, // Fondo blanco/claro
                    titleContentColor = MaterialTheme.colorScheme.onSurface // Texto oscuro
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_edit_note/-1") },
                // Hacemos el botón más cuadrado con RoundedCornerShape.
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Añadir Nota")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 8.dp) // Un poco de padding a los lados
        ) {
            items(notes) { note ->
                NoteItem(
                    note = note,
                    onEditClick = { navController.navigate("add_edit_note/${note.id}")}
                ,
                onDeleteClick = { viewModel.removeNote(note) }
                )
                // El divisor para la línea de separación sutil entre elementos.
                HorizontalDivider(
                    Modifier,
                    DividerDefaults.Thickness,
                    color = Color.LightGray.copy(alpha = 0.5f)
                )
            }
        }
    }
}

// --- COMPOSABLE REUTILIZABLE PARA CADA FILA DE LA LISTA ---
@Composable
fun NoteItem(
    note: Note,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEditClick() }
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f) // This pushes the icons to the far right.
        ) {
            Text(
                text = note.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = note.content,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        IconButton(onClick = onEditClick) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Note")
        }
        // Delete button.
        IconButton(onClick = onDeleteClick) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Note")
        }
    }
}



