# NNote Project - My Android Notes App

Hello! This is my project to create a notes app on Android. I'm using Kotlin and trying to apply the things I've been learning about modern architecture, like MVVM and Room.

This project will be constantly updated to apply other knowledge.

## Tech Stack I'm Using

*   **Language:** Kotlin, of course.
*   **Architecture:** Trying to follow MVVM (Model-View-ViewModel).
*   **Database:** Room, because it integrates very well with Coroutines.
*   **Asynchrony:** Kotlin Coroutines to keep the app responsive during database operations.
*   **Testing:** 
    *   **Google Truth:** For super-readable assertions (`assertThat`).
    *   **MockK:** To "fake" parts of my code and test one thing at a time.

## My Adventure with Tests

I've spent most of my time here lately. I've learned that there are different types of tests, and I'm applying two in this project.

### 1. Integration Tests: Does my database work?

With these tests, I check that my app can save and read notes from the database without any issues.

*   **What I'm testing:** The communication between my `NoteDao` and the Room database.
*   **How I do it:** I use a temporary, in-memory database that only exists during the test. I insert a note, retrieve it, and check that it's the same.
*   **Pattern:** I'm following the **Arrange-Act-Assert** pattern, which helps me keep the tests organized.

*(The code for this test can be found in `app/src/androidTest/java/com/example/nnote/NoteDaoTest.kt`)*

### 2. Unit Tests: Does my ViewModel do what it's supposed to?

Here, the goal is different. I don't care about the database; I just want to know if the `NotesViewModel` reacts as I expect.

*   **What I'm testing:** The internal logic of the `NotesViewModel`.
*   **How I do it:** I use `MockK` to create a "fake repository." This way, when the ViewModel tries to save a note, I can spy on that call and verify that it did it correctly, without touching a real database.
*   **Pattern:** For these, I prefer the **Given-When-Then** style. I feel it better describes the behavior I'm testing.

*(The code for this test can be found in `app/src/test/java/com/example/nnote/NotesViewModelTest.kt`)*

---

# Proyecto NNote - Mi App de Notas en Android

¡Hola! Este es mi proyecto para crear una app de notas en Android. Estoy usando Kotlin y tratando de aplicar las cosas que he ido aprendiendo sobre arquitectura moderna, como MVVM y Room.

Este proyecto se irá actualizando constantemente para aplicar otros conocimientos.

## Lo que estoy usando (mi "stack" de tecnologías)

*   **Lenguaje:** Kotlin, por supuesto.
*   **Arquitectura:** Intentando seguir MVVM (Model-View-ViewModel).
*   **Base de Datos:** Room, porque se integra muy bien con corutinas.
*   **Asincronía:** Corutinas de Kotlin para que la app no se trabe con las operaciones de la base de datos.
*   **Testing:** 
    *   **Google Truth:** Para que las comprobaciones (`assertThat`) sean súper legibles.
    *   **MockK:** Para "falsear" partes de mi código y poder probar una sola cosa a la vez.

## Mi Aventura con los Tests

Aquí es donde he pasado más tiempo últimamente. He aprendido que hay diferentes tipos de tests y estoy aplicando dos en este proyecto.

### 1. Tests de Integración: ¿Funciona mi base de datos?

Con estos tests compruebo que mi app puede guardar y leer notas de la base de datos sin problemas.

*   **Qué pruebo:** La comunicación entre mi `NoteDao` (el que tiene las queries de SQL) y la base de datos Room.
*   **Cómo lo hago:** Uso una base de datos temporal que vive solo durante el test. Inserto una nota, la recupero y compruebo que es la misma.
*   **Patrón:** Sigo la estructura **Arrange-Act-Assert** (Preparar, Actuar, Verificar), que me ayuda a mantener el test ordenado.

*(El código de este test se puede encontrar en `app/src/androidTest/java/com/example/nnote/NoteDaoTest.kt`)*

### 2. Tests Unitarios: ¿Hace mi ViewModel lo que debe?

Aquí el objetivo es diferente. No me interesa la base de datos, solo quiero saber si el `NotesViewModel` reacciona como espero.

*   **Qué pruebo:** La lógica interna del `NotesViewModel`.
*   **Cómo lo hago:** Uso `MockK` para crear un "repositorio falso". Así, cuando el ViewModel intenta guardar una nota, puedo espiar esa llamada y verificar que lo hizo correctamente, sin tocar una base de datos real.
*   **Patrón:** Para estos me gusta más el estilo **Given-When-Then** (Dado-Cuando-Entonces). Me parece que describe mejor el comportamiento que estoy probando.

*(El código de este test se puede encontrar en `app/src/test/java/com/example/nnote/NotesViewModelTest.kt`)*
