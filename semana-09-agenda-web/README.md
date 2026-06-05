# Semana 9: Agenda Web con Formulario y Persistencia

## Descripción
Aplicación web que permite agregar contactos mediante un formulario con validaciones. Los datos se guardan en un archivo JSON y persisten entre reinicios de la aplicación.

## Diagrama

```
ContactosView
     |
     v
ContactoService <- @Service
     |
     v
ManejadorJSON <- lee / escribe contactos . json
     |
     v
contactos . json
```

## Explicación de, ¿por qué la vista no toca el JSON directamente?

Los contactos se guardan en el archivo `contactos.json` en la raíz del proyecto. Cada vez que se guarda un contacto, se actualiza el archivo. Al reiniciar la aplicación, los contactos anteriores siguen disponibles.

## Cómo ejecutar

1. Entrar a la carpeta : ‘cd semana-09-agenda-web‘
2. Compilar: mvn compile
3. Ejecutar: mvn spring-boot:run
4. Luego abrir http://localhost:8080/contactos 

## Capturas

![alt text](Formulario.png)

![alt text](ContactosJSON.png)


## Ejemplo de JSON generado

```
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
  }
```
