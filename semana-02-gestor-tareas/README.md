# Semana 2: Gestor de Tareas POO

##  Descripción
Programa en Java que implementa un gestor de tareas usando Programación Orientada a Objetos (POO).

##  Funcionalidades
-  Crear tareas con ID automático, título, descripción y estado
-  Agregar tareas nuevas
-  Listar todas las tareas
-  Filtrar tareas por estado (pendientes/completadas)
-  Marcar tareas como completadas
-  Eliminar tareas por ID
-  Mostrar estadísticas (total, pendientes, completadas, % de progreso)
-  Menú interactivo con validaciones

##  Como ejecutar

1. Entrar a la carpeta : ‘cd semana-02-gestor-tareas ‘
2. Compilar : javac Main.java modelo/*.java servicio/*.java
3. Ejecutar : ‘java Main ‘

##  Diagrama simple de clases
```
+----------------+ +-------------------+
| Tarea | | GestorTareas |
+----------------+ +-------------------+
| - id: int | | - tareas: ArrayList|
| - titulo: String | - proximoId: int |
| - descripcion: String +-------------------+
| - estado: String | + agregar() |
+----------------+ | + listar() |
| + getters/setters | + listarPendientes|
| + completar() | | + listarCompletadas|
| + estaCompletada() | + completarTarea()|
| + mostrarInfo()| | + eliminarTarea() |
+----------------+ | + mostrarEstadisticas()
+-------------------+
```