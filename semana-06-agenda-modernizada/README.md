# Semana 6: Agenda Modernizada con Streams y Tests

## Descripción
Proyecto que refactoriza el GestorContactos de la Semana 5 usando Streams y Optional, y agrega tests unitarios con JUnit 5. Los datos se guardan en JSON con Gson.

## Métodos refactorizados con Streams

```
| Método | Antes (for loop) | Después (Stream) |
|--------|------------------|------------------|
| `buscarPorNombre` | for con if | `stream().filter().findFirst()` |
| `filtrarPorCategoria` | for con if + add | `stream().filter().collect()` |
| `obtenerNombres` | for con add | `stream().map().collect()` |
| `contarPorCategoria` | for con if + contador | `stream().filter().count()` |
| `listarOrdenados` | Collections.sort | `stream().sorted().collect()` |
| `agregarContacto` | for con bandera | `stream().anyMatch()` |
```

### Tests implementados (8 tests)

1. `buscarPorNombreEncuentraContactoExistente`
2. `buscarPorNombreRetornaVacioSiNoExiste`
3. `filtrarPorCategoriaDevuelveSoloCorrectos`
4. `filtrarPorCategoriaInexistenteDevuelveVacia`
5. `obtenerNombresDevuelveTodosLosNombres`
6. `contarPorCategoriaRetornaCantidadCorrecta`
7. `listarOrdenadosDevuelveOrdenAlfabetico`
8. `agregarContactoDuplicadoLanzaExcepcion`

### Resultado de mvn test (copia el bloque [INFO] Tests run: ...)

```
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.234 s -- in servicio.GestorContactosTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.445 s
[INFO] Finished at: 2026-06-05T16:38:54-04:00
[INFO] ------------------------------------------------------------------------
```

## Cómo compilar y ejecutar el programa

1. Entrar a la carpeta : ‘cd semana-06-agenda-modernizada‘
2. Compilar: mvn compile
3. Ejecutar: mvn exec:java -Dexec.mainClass="Main"

## Desiciones buscarPorNombre

- `buscarPorNombre` devuelve `Optional<Contacto>` para evitar null
- El Main maneja el Optional con `ifPresent`
- Los tests usan archivo JSON temporal (`test_contactos.json`)
- Se mantiene la persistencia JSON con Gson