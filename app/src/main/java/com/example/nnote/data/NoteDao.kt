package com.example.nnote.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes_table ORDER BY id DESC")
     fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes_table WHERE id = :noteId")
    suspend fun getNotesById(noteId: Long): Note?

    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}