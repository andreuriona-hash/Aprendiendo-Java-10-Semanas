# Semana 5: Agenda de Contactos con Excepciones y Persistencia JSON

## Descripción
Sistema de agenda de contactos que implementa manejo de excepciones personalizadas y persistencia en archivos JSON utilizando la librería Gson. Los datos se guardan automáticamente después de cada operación y se cargan al iniciar el programa.

## Estructura del Proyecto
semana-05-agenda-contactos/
├── pom.xml
├── README.md
├── src/
│   └── main/
│       └── java/
│           ├── Main.java
│           ├── modelo/
│           │   └── Contacto.java
│           ├── servicio/
│           │   └── AgendaContactos.java
│           ├── util/
│           │   └── ManejadorJSON.java
│           └── exception/
│               ├── DatoInvalidoException.java
│               ├── ContactoNoEncontradoException.java
│               └── ContactoExistenteException.java
├── data/
│   ├── contactos.json
│   └── contactos.backup.json
└── target/

## Formato JSON que usa el programa
El archivo data/contactos.json almacena los contactos en el siguiente formato:

[
  {
    "id": "C001",
    "nombre": "Juan Perez",
    "telefono": "72345678",
    "email": "juan.perez@gmail.com",
    "direccion": "Av. 6 de Agosto 123"
  }
]

## Excepciones Personalizadas

| Excepción             | Tipo                         | Cuando se lanza |
| DatoInvalidoException | Unchecked (RuntimeException) | Cuando un campo no cumple las validaciones: nombre vacio, telefono con menos de 7 digitos, email sin @ |

| ContactoNoEncontradoException | Checked (Exception)  | Cuando se busca un contacto por ID y no existe |
| ContactoExistenteException    | Checked (Exception)  | Cuando se intenta agregar un contacto con ID duplicado |

## Funcionalidades Implementadas

| Requisito | Descripcion | Estado |
| RF1 | Cargar contactos al iniciar desde contactos.json | SI |
| RF2 | Agregar contacto con validaciones | SI |
| RF3 | Listar todos los contactos en formato tabular | SI |
| RF4 | Buscar por ID exacto o por nombre parcial | SI |
| RF5 | Editar telefono y email de un contacto | SI |
| RF6 | Eliminar contacto con confirmacion | SI |
| RF7 | Guardar automaticamente despues de cada cambio | SI |
| RF8 | Backup antes de cada guardado | SI |
| RF9 | Mostrar estadisticas: total de contactos y con email | SI |

## Como instalar y ejecutar con MAVEN

Compilar:
mvn compile

Ejecutar:
mvn exec:java -Dexec.mainClass="Main"

## Validaciones implementadas
- Nombre: no puede estar vacio
- Telefono: entre 7 y 8 digitos
- Email: debe contener el simbolo @
- Direccion: opcional (puede estar vacia)

## Capturas de pantalla
Las capturas de pantalla del programa en ejecucion se encuentran en la carpeta capturas/