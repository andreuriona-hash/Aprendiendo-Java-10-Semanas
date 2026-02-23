import modelo.Tarea;
import servicio.GestorTareas;
import java.util.Scanner;

public class Main {
    static GestorTareas gestor = new GestorTareas();
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int opcion;
        
        do {
            mostrarMenu();
            opcion = leerOpcion();
            
            switch (opcion) {
                case 1: agregarTarea(); break;
                case 2: gestor.listarTodas(); break;
                case 3: gestor.listarPendientes(); break;
                case 4: gestor.listarCompletadas(); break;
                case 5: completarTarea(); break;
                case 6: eliminarTarea(); break;
                case 7: gestor.mostrarEstadisticas(); break;
                case 8: 
                    System.out.println(" ¡Hasta pronto!");
                    break;
                default:
                    System.out.println(" Opción inválida. Intente de nuevo.");
            }
            
            if (opcion != 8) {
                System.out.println("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }
            
        } while (opcion != 8);
        
        scanner.close();
    }
    
    public static void mostrarMenu() {
        System.out.println("\n===================================");
        System.out.println("      GESTOR DE TAREAS POO");
        System.out.println("===================================");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Listar todas las tareas");
        System.out.println("3. Listar tareas pendientes");
        System.out.println("4. Listar tareas completadas");
        System.out.println("5. Completar tarea");
        System.out.println("6. Eliminar tarea");
        System.out.println("7. Ver estadísticas");
        System.out.println("8. Salir");
        System.out.print("Elija una opción: ");
    }
    
    public static int leerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            return opcion;
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar entrada inválida
            return 0; // Opción inválida
        }
    }
    
    public static void agregarTarea() {
        System.out.println("\n--- AGREGAR NUEVA TAREA ---");
        
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        
        gestor.agregar(titulo, descripcion);
    }
    
    public static void completarTarea() {
        if (!gestor.hayTareas()) {
            System.out.println(" No hay tareas para completar");
            return;
        }
        
        System.out.print("ID de la tarea a completar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        
        gestor.completarTarea(id);
    }
    
    public static void eliminarTarea() {
        if (!gestor.hayTareas()) {
            System.out.println(" No hay tareas para eliminar");
            return;
        }
        
        System.out.print("ID de la tarea a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        
        gestor.eliminarTarea(id);
    }
}