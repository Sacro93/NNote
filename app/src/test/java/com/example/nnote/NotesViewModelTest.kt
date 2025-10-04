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
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class NotesViewModelTest {

    private lateinit var fakeRepository: NotesRepository
    private lateinit var viewModel: NotesViewModel

    @Before //antes de cada test se ejecuta
    fun setUpt() {

        //repositorio falso
        fakeRepository = mockk(relaxed = true)

        viewModel = NotesViewModel(fakeRepository)

        Dispatchers.setMain(Dispatchers.Unconfined)

    }

    ///luego del test limpiar el despachador
    @Before
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `al llamar a removeNote, se debe llamar al removeNote del repositorio`() {

        val testNote = Note(id = 1, title = "Test", content = "This is a test note")

        viewModel.removeNote(testNote)

        coVerify(exactly = 1) { fakeRepository.removeNote(testNote) }

    }

    @Test
    fun `al llamar a createNote, se debe llamar al addNote del repositorio`() {

        //(GIVEN)

        val testTitle = "Test"
        val testContent = "This is a test note"

        viewModel.createNote(testTitle, testContent)

        coVerify(exactly = 1) { fakeRepository.addNote(any()) }

    }




    //La segunda prueba (con slot) es como preguntarle: "¿Entregaste el paquete? Y por favor,
    // ábrelo y muéstrame que dentro está exactamente el jarrón azul que te di, y no uno rojo".

    @Test
    fun `al llamar a updateNote, se debe llamar al updateNote del repositorio CON la nota modificada`() {

        val notaOriginal = Note(id = 1L, title = "Título Original", content = "Contenido Original")

        val notaModificada = notaOriginal.copy(title = "Título Modificado")
        val noteSlot = slot<Note>()

        coEvery { fakeRepository.updateNote(capture(noteSlot)) } returns Unit


        viewModel.upDateNote(notaModificada)

        coVerify(exactly = 1) { fakeRepository.updateNote(any()) }

        assertThat(noteSlot.captured.title).isEqualTo("Título Modificado")
        assertThat(noteSlot.captured.id).isEqualTo(notaOriginal.id)
    }
}