import modelo.Contacto;
import servicio.AgendaContactos;
import exception.ContactoExistenteException;
import exception.ContactoNoEncontradoException;
import exception.DatoInvalidoException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main  {
    
    private static AgendaContactos agenda = new AgendaContactos();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int opcion;
        
        do {
            mostrarMenu();
            opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    agregarContacto();
                    break;
                case 2:
                    listarContactos();
                    break;
                case 3:
                    buscarContacto();
                    break;
                case 4:
                    editarContacto();
                    break;
                case 5:
                    eliminarContacto();
                    break;
                case 6:
                    agenda.mostrarEstadisticas();
                    break;
                case 7:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
            
            if (opcion != 7) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
            
        } while (opcion != 7);
        
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\n==========================================");
        System.out.println("         AGENDA DE CONTACTOS");
        System.out.println("==========================================");
        System.out.println("1. Agregar contacto");
        System.out.println("2. Listar todos los contactos");
        System.out.println("3. Buscar contacto");
        System.out.println("4. Editar contacto");
        System.out.println("5. Eliminar contacto");
        System.out.println("6. Ver estadísticas");
        System.out.println("7. Salir");
        System.out.print("Elija una opción: ");
    }
    
    private static int leerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();
            return opcion;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }
    
    private static void agregarContacto() {
        System.out.println("\n--- AGREGAR NUEVO CONTACTO ---");
        
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Teléfono (7-8 dígitos): ");
            String telefono = scanner.nextLine();
            
            System.out.print("Email (debe contener @): ");
            String email = scanner.nextLine();
            
            System.out.print("Dirección (opcional): ");
            String direccion = scanner.nextLine();
            
            agenda.agregar(nombre, telefono, email, direccion);
            System.out.println("Contacto agregado correctamente.");
            
        } catch (DatoInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ContactoExistenteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void listarContactos() {
        ArrayList<Contacto> lista = agenda.listarTodos();
        
        if (lista.isEmpty()) {
            System.out.println("\nNo hay contactos registrados.");
            return;
        }
        
        System.out.println("\n=== LISTA DE CONTACTOS (" + lista.size() + ") ===");
        System.out.println("ID\t| NOMBRE\t| TELÉFONO\t| EMAIL");
        System.out.println("----------------------------------------");
        
        for (Contacto c : lista) {
            System.out.printf("%s\t| %s\t| %s\t| %s\n",
                    c.getId(),
                    c.getNombre(),
                    c.getTelefono(),
                    c.getEmail());
        }
    }
    
    private static void buscarContacto() {
        System.out.println("\n--- BUSCAR CONTACTO ---");
        System.out.println("1. Buscar por ID exacto");
        System.out.println("2. Buscar por nombre (parcial)");
        System.out.print("Elija opción: ");
        
        int opcion = leerOpcion();
        
        switch (opcion) {
            case 1:
                buscarPorId();
                break;
            case 2:
                buscarPorNombre();
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }
    
    private static void buscarPorId() {
        System.out.print("Ingrese ID (ej. C001): ");
        String id = scanner.nextLine();
        
        try {
            Contacto c = agenda.buscarPorId(id);
            System.out.println("\nContacto encontrado:");
            System.out.println(c.toStringDetalle());
        } catch (ContactoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void buscarPorNombre() {
        System.out.print("Ingrese nombre o parte del nombre: ");
        String nombre = scanner.nextLine();
        
        ArrayList<Contacto> resultados = agenda.buscarPorNombre(nombre);
        
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron contactos con ese nombre.");
            return;
        }
        
        System.out.println("\nResultados encontrados (" + resultados.size() + "):");
        for (Contacto c : resultados) {
            System.out.println(c);
        }
    }
    
    private static void editarContacto() {
        System.out.println("\n--- EDITAR CONTACTO ---");
        System.out.print("Ingrese ID del contacto a editar: ");
        String id = scanner.nextLine();
        
        try {
            Contacto c = agenda.buscarPorId(id);
            System.out.println("\nContacto actual:");
            System.out.println(c.toStringDetalle());
            
            System.out.println("\nIngrese los nuevos datos (Enter para mantener el actual):");
            
            System.out.print("Nuevo teléfono: ");
            String telefono = scanner.nextLine();
            
            System.out.print("Nuevo email: ");
            String email = scanner.nextLine();
            
            agenda.editar(id, telefono.isEmpty() ? null : telefono, 
                                email.isEmpty() ? null : email);
            
            System.out.println("Contacto actualizado correctamente.");
            
        } catch (ContactoNoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (DatoInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void eliminarContacto() {
        System.out.println("\n--- ELIMINAR CONTACTO ---");
        System.out.print("Ingrese ID del contacto a eliminar: ");
        String id = scanner.nextLine();
        
        try {
            Contacto c = agenda.buscarPorId(id);
            System.out.println("\nContacto encontrado:");
            System.out.println(c);
            
            System.out.print("\n¿Está seguro de eliminar? (s/n): ");
            String confirmacion = scanner.nextLine();
            
            if (confirmacion.equalsIgnoreCase("s")) {
                agenda.eliminar(id);
                System.out.println("Contacto eliminado correctamente.");
            } else {
                System.out.println("Operación cancelada.");
            }
            
        } catch (ContactoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}
