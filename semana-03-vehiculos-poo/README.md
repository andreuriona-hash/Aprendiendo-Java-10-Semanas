# Semana 3: Gestión de Vehículos POO

##  Descripción
Sistema de gestión de flota vehicular que demuestra los conceptos de **herencia, polimorfismo e interfaces** en Java.

##  Diagrama de Clases

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