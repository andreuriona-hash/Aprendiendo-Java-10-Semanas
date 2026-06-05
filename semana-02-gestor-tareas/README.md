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
| - id | | - tareas |
| - titulo | | - proximoId |
| - descripcion | +-------------------+
| - estado | | + agregar() |
+----------------+ | + listarTodas() |
| + Tarea() |<>------->| + listarPendientes|
| + getters/setters | + listarCompletadas|
| + completar() | | + completarTarea()|
| + estaCompletada() | + eliminarTarea() |
| + mostrarInfo()| | + mostrarEstadisticas()
+----------------+ +-------------------+
```
## Capturas
![ejecucion 1](<capturas/ejecucion 1.png>)
![ejecucion 2](<capturas/ejecucion 2.png>)
![ejecucion 3](<capturas/ejecucion 3.png>)
![ejecucion 4](<capturas/ejecucion 4.png>)
![ejecucion 5](<capturas/ejecucion 5.png>)
![ejecucion 6](<capturas/ejecucion 6.png>)