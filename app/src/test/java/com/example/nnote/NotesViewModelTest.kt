package com.example.nnote

import com.example.nnote.data.Note
import com.example.nnote.data.NotesRepository
import com.example.nnote.ui.viewmodel.NotesViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

/*Given-When-Then*/

@ExperimentalCoroutinesApi
class NotesViewModelTest {

    private lateinit var fakeRepository: NotesRepository
    private lateinit var viewModel: NotesViewModel

    @Before
    fun setUp() {

        fakeRepository = mockk(relaxed = true)
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = NotesViewModel(fakeRepository)


    }

    @Before
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun removeNote_callsRepository_withCorrectNote() {

        // Given (Arrange)
        val testNote = Note(id = 1, title = "Test", content = "This is a test note")

        // When (Act)
        viewModel.removeNote(testNote)

        // Then (Assert)
        // Verificamos que la función removeNote del repositrio fue llamado exactamente una vez
        // y con el objeto 'testNote' correcto.
        coVerify(exactly = 1) { fakeRepository.removeNote(testNote) }

    }

    @Test
    fun addNote_callsRepository_withCorrectNote() {

        // Given (Arrange)
        val testTitle = "Test"
        val testContent = "This is a test note"
        val noteSlot = slot<Note>()

        coEvery { fakeRepository.addNote(capture(noteSlot)) } returns Unit

        // When (Act)
        viewModel.createNote(testTitle, testContent)

        // Then (Assert)
        // Verificamos que la nota capturada tiene los datos correctos.
        assertThat(noteSlot.captured.title).isEqualTo(testTitle)
        assertThat(noteSlot.captured.content).isEqualTo(testContent)
    }

    @Test
    fun updateNote_callsRepository_withCorrectNote() {

        val noteOriginal = Note(id = 1L, title = "Título Original", content = "Contenido Original")

        val noteModification = noteOriginal.copy(title = "Título Modificado")
        val noteSlot = slot<Note>()


        coEvery { fakeRepository.updateNote(capture(noteSlot)) } returns Unit


        viewModel.upDateNote(noteModification)

        coVerify(exactly = 1) { fakeRepository.updateNote(any()) }

        //slot para "abrir el paquete" que se envió y verificar que la nota que llegó al repositorio tenía el título realmente modificado.

        assertThat(noteSlot.captured.title).isEqualTo("Título Modificado")
        assertThat(noteSlot.captured.id).isEqualTo(noteOriginal.id)
    }
}