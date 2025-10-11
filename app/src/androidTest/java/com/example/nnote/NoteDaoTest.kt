package com.example.nnote

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nnote.data.Note
import com.example.nnote.data.NoteDao
import com.example.nnote.data.NoteDatabase
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat


//patrón Arrange-Act-Assert (AAA)

@RunWith(AndroidJUnit4::class)
class NoteDaoTest {

    private lateinit var database: NoteDatabase
    private lateinit var dao: NoteDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.noteDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertNote_retrievesSameNoteById() = runTest {

        val originalNote = Note(title = "integration test", content = "Thinks")

        val newId = dao.insertNote(originalNote)
        val recoveredNote = dao.getNoteById(newId)

        val expectedNote = originalNote.copy(id = 1L)
        assertThat(recoveredNote).isEqualTo(expectedNote.copy(id = newId))
    }
}