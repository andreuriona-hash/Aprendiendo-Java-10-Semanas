# Semana 6: Agenda Modernizada con Streams y Tests

## Descripción
Proyecto que refactoriza el GestorContactos de la Semana 5 usando Streams y Optional, y agrega tests unitarios con JUnit 5. Los datos se guardan en JSON con Gson.

## Métodos refactorizados con Streams

| Método | Antes (for loop) | Después (Stream) |
|--------|------------------|------------------|
| `buscarPorNombre` | for con if | `stream().filter().findFirst()` |
| `filtrarPorCategoria` | for con if + add | `stream().filter().collect()` |
| `obtenerNombres` | for con add | `stream().map().collect()` |
| `contarPorCategoria` | for con if + contador | `stream().filter().count()` |
| `listarOrdenados` | Collections.sort | `stream().sorted().collect()` |
| `agregarContacto` | for con bandera | `stream().anyMatch()` |

## Tests unitarios (JUnit 5)

### Tests implementados (8 tests)

1. `buscarPorNombreEncuentraContactoExistente`
2. `buscarPorNombreRetornaVacioSiNoExiste`
3. `filtrarPorCategoriaDevuelveSoloCorrectos`
4. `filtrarPorCategoriaInexistenteDevuelveVacia`
5. `obtenerNombresDevuelveTodosLosNombres`
6. `contarPorCategoriaRetornaCantidadCorrecta`
7. `listarOrdenadosDevuelveOrdenAlfabetico`
8. `agregarContactoDuplicadoLanzaExcepcion`

### Resultado de los tests

mvn test

[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS

## Decisiones de diseño

- `buscarPorNombre` devuelve `Optional<Contacto>` para evitar null y obligar al que llama a manejar el caso de no encontrado.
- El Main maneja el Optional con `ifPresent` y `orElse`.
- Los tests usan archivo JSON temporal (`test_contactos.json`) para no afectar los datos reales.
- Se mantiene la persistencia JSON con Gson y backup automático.

## Contactos de ejemplo

Al iniciar el programa con la agenda vacía, se cargan automáticamente 5 contactos de ejemplo:

| Nombre | Teléfono | Email | Categoría |
|--------|----------|-------|-----------|
| Juan Perez | 72345678 | juan.perez@gmail.com | Amigos |
| Maria Lopez | 79876543 | maria.lopez@hotmail.com | Trabajo |
| Pedro Quispe | 68112233 | pedro.quispe@yahoo.com | Familia |
| Ana Torres | 71234567 | ana.torres@gmail.com | Amigos |
| Carlos Mamani | 72456890 | carlos.mamani@hotmail.com | Trabajo |

## Cómo ejecutar

```bash
# Compilar
mvn compile

# Ejecutar el programa
mvn exec:java -Dexec.mainClass="Main"

# Ejecutar los tests
mvn test

## Decisiones de diseño

### 1. Por qué `buscarPorNombre` devuelve `Optional<Contacto>` y no `null`
- `null` es peligroso porque quien llama al método puede olvidar verificarlo y causar un `NullPointerException` en tiempo de ejecución.
- `Optional` comunica explícitamente que el valor puede no existir, obligando al que llama a manejarlo (con `ifPresent`, `orElse`, etc.).
- El compilador no puede evitar el uso de `null`, pero sí puede guiar el uso correcto de `Optional`.

### 2. Por qué usar Streams en lugar de for loops
- Código más legible: las operaciones se encadenan y se leen como una oración.
- Menos código: elimina variables temporales y bucles manuales.
- Menos errores: al eliminar la manipulación manual de índices y listas, se reducen errores comunes.

### 3. Por qué los tests usan archivo JSON temporal
- Evita que los datos reales de `contactos.json` se vean afectados por los tests.
- Cada test comienza con una lista limpia (el `@BeforeEach` borra los archivos temporales).
- Permite ejecutar tests en paralelo sin interferencia.

### 4. Por qué se usa `@BeforeEach` en los tests
- Garantiza que cada test comience con el mismo estado inicial (4 contactos de prueba).
- Evita que un test afecte a otro al compartir datos.

### 5. Por qué se usa `assertThrows` para duplicados
- Verifica que la excepción se lance cuando se intenta agregar un contacto con nombre repetido.
- Valida que la validación de negocio funciona correctamente.