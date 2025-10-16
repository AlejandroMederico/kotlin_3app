# Proyecto Android con Múltiples Apps

Este repositorio contiene una colección de aplicaciones de Android, cada una diseñada para demostrar diferentes conceptos y funcionalidades del desarrollo de Android con Kotlin.

## Aplicaciones Incluidas

1.  **To-Do App:** Una aplicación de lista de tareas.
2.  **IMC App:** Una calculadora de Índice de Masa Corporal (IMC).
3.  **First App:** Una aplicación sencilla para pasar datos entre `Activities`.

---

## 1. To-Do App

Una sencilla aplicación de lista de tareas para Android desarrollada en Kotlin.

### Descripción

Esta aplicación permite a los usuarios gestionar sus tareas diarias. Las tareas se pueden organizar en diferentes categorías y se muestran en una lista. Los usuarios pueden agregar nuevas tareas, marcarlas como completadas y filtrarlas según la categoría.

### Características

*   **Añadir Tareas:** Agrega nuevas tareas con una descripción y una categoría a través de un cuadro de diálogo.
*   **Lista de Tareas:** Visualiza todas las tareas en una lista vertical.
*   **Categorías:** Organiza las tareas en categorías predefinidas (Negocios, Personal, Otros).
*   **Filtrar por Categoría:** Filtra la lista de tareas seleccionando una o más categorías.
*   **Marcar como Completadas:** Marca o desmarca tareas como completadas.

---

## 2. IMC App

Una aplicación para calcular el Índice de Masa Corporal (IMC).

### Descripción

El usuario puede introducir su altura y peso, y la aplicación calculará y mostrará su IMC, junto con una clasificación del resultado (por ejemplo, bajo peso, normal, sobrepeso).

### Características

*   **Entrada de Datos:** Selección de género, e introducción de altura y peso mediante controles deslizantes (`Slider`).
*   **Cálculo en Tiempo Real:** La interfaz se actualiza para reflejar los valores seleccionados.
*   **Pantalla de Resultados:** Muestra el IMC calculado y una descripción del resultado.

---

## 3. First App

Una aplicación básica que demuestra cómo iniciar una nueva `Activity` y pasarle datos.

### Descripción

La pantalla principal contiene un campo de texto donde el usuario puede escribir su nombre. Al pulsar un botón, se abre una segunda pantalla que muestra un saludo personalizado con el nombre introducido.

### Características

*   **Entrada de Texto:** Un `EditText` para que el usuario escriba su nombre.
*   **Navegación entre Activities:** Usa un `Intent` para iniciar una segunda `Activity`.
*   **Paso de Datos:** Pasa el nombre del usuario de la primera `Activity` a la segunda utilizando los `extras` del `Intent`.

## Tecnologías Utilizadas

*   **Kotlin:** Lenguaje de programación principal.
*   **Android SDK:** Kit de desarrollo de software para Android.
*   **View Binding / FindViewById:** Para acceder a las vistas del layout.
*   **RecyclerView:** Para mostrar listas eficientes de tareas y categorías en la To-Do App.
*   **Material Components:** Para elementos de la interfaz de usuario.
*   **Intents:** Para la comunicación entre `Activities`.

## Cómo Empezar

1.  Clona este repositorio.
2.  Ábrelo en Android Studio.
3.  Sincroniza el proyecto con los archivos de Gradle.
4.  Selecciona la aplicación que deseas ejecutar en la configuración de ejecución (por ejemplo, `app`, `imcapp`, o `firstapp`).
5.  Ejecuta la aplicación en un emulador o en un dispositivo físico.
