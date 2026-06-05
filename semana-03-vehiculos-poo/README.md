# Semana 3: Gestión de Vehículos POO

##  Descripción
Sistema de gestión de flota vehicular que demuestra los conceptos de **herencia, polimorfismo e interfaces** en Java.

##  Diagrama de Clases
```
                [   Vehiculo      ] (abstract)
                         │
     +───────────────────+───────────────────+
     │                   │                   │
 [  Auto  ]         [  Moto   ]          [  Camion  ]
     │                   │
     +                   +
     │                   │
[ AutoElectrico   ] [ MotoElectrica   ]
           │                   │
           +────────+──────────+
                    │
             <<Electrico>>    (interface)
```
## Decision de diseño
- ¿Por que Vehiculo es abstracta?
    No tiene sentido instanciar un "vehículo genérico", solo vehículos concretos como Auto o Moto.
- ¿Por que Electrico es interfaz y no otra clase?
    Porque un vehículo eléctrico "puede hacer" cosas como cargar batería, pero no es un tipo de vehículo en sí mismo.

## Como ejecutar

1. Entrar a la carpeta : ‘cd semana-03-vehiculos-poo‘
2. compilar: javac Main.java modelo/*.java servicio/*.java
3. Ejecutar: ‘java Main‘

## Ejemplo de salida del programa corriendo
```
==========================================
      GESTIÓN DE VEHÍCULOS POO
==========================================
1. Agregar vehículo
2. Listar todos
3. Filtrar por tipo
4. Ver eléctricos y batería
5. Cargar baterías bajas
6. Demostrar polimorfismo
7. Estadísticas
8. Salir
Elija una opción:

```