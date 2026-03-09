# Semana 4: Sistema de Estudiantes con Collections

##  Descripción
Sistema de gestión de estudiantes que utiliza tres colecciones diferentes del Java Collections Framework, cada una con un rol específico: búsqueda rápida por carnet, ranking automático por promedio e historial de operaciones.

##  Estructura del Proyecto 
 semana-04-estudiantes-collections/
├── Main.java
├── README.md
├── modelo/
│   └── Estudiante.java
├── servicio/
│   └── GestorEstudiantes.java
└── capturas/
├── ranking.png
├── top5.png
└──menu.png
## Tabla comparativa para el README 
| Colección | Rol en el sistema | ¿Por qué? |
|-----------|-------------------|-----------|
| HashMap<String, Estudiante> | Búsqueda por carnet |  Permite encontrar un estudiante al instante sin recorrer toda la lista, ideal para
 buscar por identificador único. |
 
| TreeSet<Estudiante> | Ranking automático por promedio | Mantiene el orden siempre. Al implementar Comparable, los estudiantes se ordenan automáticamente de menor a mayor promedio. El desempate por carnet evita que se pierdan estudiantes con el mismo promedio. |

| ArrayList<String>| Historial de operaciones | Guarda el orden de llegada. Simple y eficiente para registrar secuencias de eventos. |

##  Funcionalidades Implementadas

| # | Requisito | Estado |
|---|-----------|--------|
| RF1 | Registrar estudiantes (carnet único) |
| RF2 | Buscar por carnet | 
| RF3 | Actualizar promedio |
| RF4 | Eliminar estudiante |
| RF5 | Ver ranking completo |
| RF6 | Ver top 5 |
| RF7 | Filtrar por carrera |
| RF8 | Estadísticas (total, promedio general, mejor/peor) |
| RF9 | Historial de operaciones |

##  Detalles Técnicos Importantes

### 1. Desempate en Comparable
public int compareTo(Estudiante otro) {
    int cmp = Double.compare(this.promedio, otro.promedio);
    if (cmp == 0) return this.carnet.compareTo(otro.carnet);
    return cmp;
}
##  Capturas
Las capturas de pantalla están en la carpeta  capturas/ e incluyen:
- Menu Principal 
- Ranking completo de estudiantes
- Top 5 mejores promedios