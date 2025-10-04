package com.example.nnote.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*Esto define qué es una nota. Al añadirle la anotación @Entity, le hemos dicho a Room:
"Oye, cada objeto de tipo Note que yo cree es una fila en una tabla de la base de datos". Es la unidad de información fundamental.*/
@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String
)