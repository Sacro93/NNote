package com.example.nnote

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*En resumen, la clase NoteDatabase usa las anotaciones de Room para definir la estructura de la
base de datos y un patrón Singleton clásico para proporcionar un punto de acceso único, seguro y eficiente a esa base de datos para toda tu aplicación.*/
// 1. @Database: Anotación que marca la clase como una base de datos de Room.
//    - entities: Aquí listamos todas nuestras clases @Entity.
//    - version: Es el número de versión de la base de datos. Empieza en 1.
@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    // 2. Función abstracta para cada DAO:
    //   Dentro de tu clase de base de datos, declaras una función abstract por cada interfaz
    //   @Dao que tengas. Esto es una promesa: le estás diciendo a Room, "Necesito acceso a un NoteDao"
    abstract fun noteDao(): NoteDao

    // 3. Companion Object (Patrón Singleton):
    //    Este bloque de código se asegura de que solo exista UNA instancia de la base de datos en toda la aplicación, lo cual es muy importante para el rendimiento.
    /* Esto crea un objeto de tipo estático asociado a la clase, permitiéndote tener campos y métodos a los que se puede acceder sin crear una instancia de la clase */
    companion object {
        // @Volatile asegura que el valor de INSTANCE siempre esté actualizado y sea el mismo para todos los hilos.
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            // INSTANCE ?: ... (El Operador Elvis): Significa "si INSTANCE no es null,
            // devuélvelo. En caso contrario (:), ejecuta el código en el bloque".
            // Esto asegura que una vez que la base de datos se ha creado, nunca la volvemos a crear.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database" // El nombre del archivo de la base de datos en el móvil.
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}