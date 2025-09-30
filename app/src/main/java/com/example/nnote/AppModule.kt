package com.example.nnote

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//Enseñar a Hilt a Construir Nuestras Piezas (El Módulo)
//Ahora le damos a Hilt las "recetas" para construir nuestro Repository, DAO y Database. Esto se hace en un Módulo.

/*Hilt encuentra un archivo anotado con @Module (tu AppModule).

Lee la anotación @InstallIn(SingletonComponent::class), que le dice: "Ok, las recetas que hay aquí dentro son para construir objetos que
 vivirán durante toda la vida de la aplicación".

Registra todas las funciones @Provides que hay dentro de ese módulo en su "base de conocimiento".*/
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Suppress("unused")
    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return NoteDatabase.getDatabase(context)
    }

    @Suppress("unused")
    @Provides
    @Singleton
    fun provideNoteDao(database: NoteDatabase): NoteDao {
        return database.noteDao()
    }

    @Suppress("unused")
    @Provides
    @Singleton
    fun provideNotesRepository(noteDao: NoteDao): NotesRepository {
        return NotesRepository(noteDao)
    }
}