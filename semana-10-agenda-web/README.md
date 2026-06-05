# Semana 10: Agenda Completa

## Descripción
La Agenda Completa es una aplicación web desarrollada con Spring Boot y Vaadin que permite gestionar contactos y eventos de forma integrada. Ofrece un CRUD completo para ambas entidades con validaciones en tiempo real, persistencia en archivos JSON y una interfaz intuitiva basada en Grid, formularios y diálogos de confirmación. Los datos se mantienen entre sesiones, y la arquitectura separa claramente la vista, el servicio y la persistencia.

## Diagrama

```
ContactosView             EventosView
    |                          |
    v                          v
ContactoService        EventoService <- @Service
    |                          |
    v                          v
ManejadorJSON           ManejadorJSON
    |                          |
    v                          v
contactos.json          eventos.json

```


## Explicación del flujo de datos: Vista → Service → ManejadorJSON → JSON

- **Vista → Service**: La vista (ContactosView o EventosView) captura los datos del usuario y llama a los métodos del servicio correspondiente.

- **Service → ManejadorJSON**: El servicio procesa los datos y utiliza ManejadorJSON para leer o escribir en el archivo JSON.

- **ManejadorJSON → JSON**: ManejadorJSON serializa los objetos a JSON y los guarda en el archivo correspondiente.


## Cómo ejecutar

1. Entrar a la carpeta: ‘cd semana-10-agenda-web‘
2. Compilar: mvn compile
3. Ejecutar: mvn spring-boot:run
4. Luego abrir: http://localhost:8080/contactos 

## Capturas

![contactos](contactos.png)
![contactos-JSON](contactos-JSON.png)
![eventos](eventos.png)
![eventos-JSON](eventos-JSON.png)

## Ejemplo del contactos.json

```
[
  {
    "nombre": "Ana Quispe",
    "email": "ana@gmail.com",
    "telefono": "67209471"
  },
  {
    "nombre": "Andre Uriona",
    "email": "andre@loco.com",
    "telefono": "67209471"
  },
  {
    "nombre": "Leonel Uriona",
    "email": "leo@gmail.com",
    "telefono": "68090383"
  },
  {
    "nombre": "Monica Fernandez",
    "email": "monica@gmail.com",
    "telefono": "75294988"
  },
  {
    "nombre": "Paola Uriona",
    "email": "paola@gmail.com",
    "telefono": "78456521"
  },
  {
    "nombre": "Adolfo Uriona",
    "email": "adolfo@gmail.com",
    "telefono": "75211411"
  },
  {
    "nombre": "Gabriela Tinta",
    "email": "Gabriela@gmail.com",
    "telefono": "45659812"
  }
]
```

## Ejemplo de eventos.json generados

```
[
  {
    "titulo": "Reunion con equipo",
    "fecha": "2026-05-03",
    "descripcion": "Revision final del proyecto"
  },
  {
    "titulo": "Cumpleaños de M. Monica",
    "fecha": "2026-05-05",
    "descripcion": "Comprar regalo, y celebrar"
  }
]
```

## Autor 

Uriona Fernandez Andre Sebastian
//
