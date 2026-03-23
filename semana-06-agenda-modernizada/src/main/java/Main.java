import modelo.Contacto;
import servicio.GestorContactos;
import exception.ContactoDuplicadoException;
import exception.DatoInvalidoException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    
    private static GestorContactos gestor = new GestorContactos();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            switch (opcion) {
                case 1: agregarContacto(); break;
                case 2: buscarPorNombre(); break;
                case 3: listarTodos(); break;
                case 4: listarOrdenados(); break;
                case 5: filtrarPorCategoria(); break;
                case 6: verNombres(); break;
                case 7: System.out.println("Adios"); break;
                default: System.out.println("Opcion invalida");
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
        System.out.println("2. Buscar por nombre");
        System.out.println("3. Listar todos");
        System.out.println("4. Listar ordenados por nombre");
        System.out.println("5. Filtrar por categoria");
        System.out.println("6. Ver solo nombres");
        System.out.println("7. Salir");
        System.out.print("Elija una opcion: ");
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
            System.out.print("Telefono (7-8 digitos): ");
            String telefono = scanner.nextLine();
            System.out.print("Email (debe contener @): ");
            String email = scanner.nextLine();
            System.out.print("Categoria: ");
            String categoria = scanner.nextLine();
            System.out.print("Direccion (opcional): ");
            String direccion = scanner.nextLine();
            gestor.agregarContacto(new Contacto(nombre, telefono, email, categoria, direccion));
            System.out.println("Contacto agregado correctamente");
        } catch (ContactoDuplicadoException | DatoInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void buscarPorNombre() {
        System.out.print("\nIngrese nombre a buscar: ");
        String nombre = scanner.nextLine();
        Optional<Contacto> resultado = gestor.buscarPorNombre(nombre);
        if (resultado.isPresent()) {
            System.out.println("Contacto encontrado: " + resultado.get());
        } else {
            System.out.println("No se encontro ningun contacto con ese nombre");
        }
    }
    
    private static void listarTodos() {
        List<Contacto> lista = gestor.obtenerTodos();
        if (lista.isEmpty()) {
            System.out.println("\nNo hay contactos registrados");
            return;
        }
        System.out.println("\n=== LISTA DE CONTACTOS (" + lista.size() + ") ===");
        for (Contacto c : lista) System.out.println(c);
    }
    
    private static void listarOrdenados() {
        List<Contacto> lista = gestor.listarOrdenados();
        if (lista.isEmpty()) {
            System.out.println("\nNo hay contactos registrados");
            return;
        }
        System.out.println("\n=== CONTACTOS ORDENADOS POR NOMBRE ===");
        for (Contacto c : lista) System.out.println(c);
    }
    
    private static void filtrarPorCategoria() {
        System.out.print("\nIngrese categoria: ");
        String categoria = scanner.nextLine();
        List<Contacto> resultados = gestor.filtrarPorCategoria(categoria);
        if (resultados.isEmpty()) {
            System.out.println("No hay contactos en la categoria: " + categoria);
            return;
        }
        System.out.println("\nContactos en " + categoria + " (" + resultados.size() + "):");
        for (Contacto c : resultados) System.out.println(c);
    }
    
    private static void verNombres() {
        List<String> nombres = gestor.obtenerNombres();
        if (nombres.isEmpty()) {
            System.out.println("\nNo hay contactos registrados");
            return;
        }
        System.out.println("\n=== NOMBRES DE CONTACTOS ===");
        for (String nombre : nombres) System.out.println(nombre);
    }
}