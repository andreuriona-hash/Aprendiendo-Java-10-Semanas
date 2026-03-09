import modelo.Estudiante;
import servicio.GestorEstudiantes;
import java.util.Scanner;

public class Main {
    
    static GestorEstudiantes gestor = new GestorEstudiantes();
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        cargarDatos();
        
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            
            switch (opcion) {
                case 1: agregarEstudiante(); break;
                case 2: buscarEstudiante(); break;
                case 3: actualizarPromedio(); break;
                case 4: eliminarEstudiante(); break;
                case 5: gestor.mostrarRanking(); break;
                case 6: gestor.mostrarTop5(); break;
                case 7: filtrarPorCarrera(); break;
                case 8: gestor.mostrarEstadisticas(); break;
                case 9: gestor.mostrarHistorial(); break;
                case 0: 
                    System.out.println(" ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
            
            if (opcion != 0) {
                System.out.println("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }
            
        } while (opcion != 0);
        
        scanner.close();
    }
    
    public static void mostrarMenu() {
        System.out.println("\n==========================================");
        System.out.println("    SISTEMA DE ESTUDIANTES - COLLECTIONS");
        System.out.println("============================================");
        System.out.println("1. Agregar estudiante");
        System.out.println("2. Buscar por carnet");
        System.out.println("3. Actualizar promedio");
        System.out.println("4. Eliminar estudiante");
        System.out.println("5. Ver ranking completo");
        System.out.println("6. Ver top 5");
        System.out.println("7. Filtrar por carrera");
        System.out.println("8. Estadísticas");
        System.out.println("9. Ver historial");
        System.out.println("0. Salir");
        System.out.print("Elija una opción: ");
    }
    
    public static int leerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); 
            return opcion;
        } catch (Exception e) {
            scanner.nextLine(); 
            return -1;
        }
    }
    
    public static void agregarEstudiante() {
        System.out.println("\n--- AGREGAR ESTUDIANTE ---");
        
        System.out.print("Carnet: ");
        String carnet = scanner.nextLine();
        
        if (gestor.existeCarnet(carnet)) {
            System.out.println(" Error: El carnet ya existe");
            return;
        }
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Carrera: ");
        String carrera = scanner.nextLine();
        
        System.out.print("Promedio: ");
        double promedio = scanner.nextDouble();
        scanner.nextLine();
        
        Estudiante e = new Estudiante(carnet, nombre, carrera, promedio);
        
        if (gestor.agregar(e)) {
            System.out.println(" Estudiante agregado correctamente");
        } else {
            System.out.println(" Error al agregar estudiante");
        }
    }
    
    public static void buscarEstudiante() {
        System.out.println("\n--- BUSCAR ESTUDIANTE ---");
        System.out.print("Carnet: ");
        String carnet = scanner.nextLine();
        
        Estudiante e = gestor.buscar(carnet);
        
        if (e != null) {
            System.out.println("\n Estudiante encontrado:");
            System.out.println(e);
        } else {
            System.out.println(" Carnet no encontrado");
        }
    }
    
    public static void actualizarPromedio() {
        System.out.println("\n--- ACTUALIZAR PROMEDIO ---");
        System.out.print("Carnet: ");
        String carnet = scanner.nextLine();
        
        if (!gestor.existeCarnet(carnet)) {
            System.out.println(" Carnet no encontrado");
            return;
        }
        
        System.out.print("Nuevo promedio: ");
        double nuevoProm = scanner.nextDouble();
        scanner.nextLine();
        
        if (gestor.actualizarPromedio(carnet, nuevoProm)) {
            System.out.println(" Promedio actualizado correctamente");
        } else {
            System.out.println(" Error al actualizar");
        }
    }
    
    public static void eliminarEstudiante() {
        System.out.println("\n--- ELIMINAR ESTUDIANTE ---");
        System.out.print("Carnet: ");
        String carnet = scanner.nextLine();
        
        if (gestor.eliminar(carnet)) {
            System.out.println(" Estudiante eliminado correctamente");
        } else {
            System.out.println(" Carnet no encontrado");
        }
    }
    
    public static void filtrarPorCarrera() {
        System.out.println("\n--- FILTRAR POR CARRERA ---");
        System.out.print("Ingrese carrera: ");
        String carrera = scanner.nextLine();
        
        gestor.filtrarPorCarrera(carrera);
    }
    
    public static void cargarDatos() {
        System.out.println("Cargando datos...");
        
        // Datos
        gestor.agregar(new Estudiante("2021001", "Ana Torres", "Sistemas", 75.5));
        gestor.agregar(new Estudiante("2021002", "Carlos Quispe", "Industrial", 88.0));
        gestor.agregar(new Estudiante("2021003", "Maria Condori", "Sistemas", 92.3));
        gestor.agregar(new Estudiante("2021004", "Luis Mamani", "Civil", 61.0));
        gestor.agregar(new Estudiante("2021005", "Rosa Flores", "Industrial", 79.5));
        gestor.agregar(new Estudiante("2021006", "Pedro Vargas", "Sistemas", 55.0));
        gestor.agregar(new Estudiante("2021007", "Silvia Choque", "Civil", 83.5));
        gestor.agregar(new Estudiante("2021008", "Hugo Espejo", "Industrial", 70.0));
        gestor.agregar(new Estudiante("2021009", "Patricia Lima", "Sistemas", 95.0));
        gestor.agregar(new Estudiante("2021010", "Diego Arce", "Civil", 67.5));
        
        System.out.println(" 10 estudiantes");
    }
}