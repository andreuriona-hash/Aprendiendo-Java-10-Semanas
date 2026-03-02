import modelo.*;
import servicio.GestorVehiculos;
import java.util.Scanner;

public class Main {
    static GestorVehiculos gestor = new GestorVehiculos();
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // === PARTE 1: DEMOSTRACIÓN DE POLIMORFISMO ===
        System.out.println("==========================================");
        System.out.println("  DEMOSTRACIÓN DE POLIMORFISMO");
        System.out.println("==========================================");
        
        // Crear una flota mixta
        gestor.agregar(new Auto("Toyota", "Corolla", 2024, 4));
        gestor.agregar(new Moto("Honda", "CB500", 2023, false));
        gestor.agregar(new Camion("Volvo", "FH16", 2022, 24.0));
        gestor.agregar(new AutoElectrico("Tesla", "Model 3", 2024, 4, 80));
        gestor.agregar(new MotoElectrica("NIU", "MQi+", 2023, false, 60));
        
        // Demostrar polimorfismo
        gestor.demostrarPolimorfismo();
        
        System.out.println("\n Demostración completada. Presione ENTER para continuar...");
        scanner.nextLine();
        
        // === PARTE 2: MENÚ INTERACTIVO ===
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            
            switch (opcion) {
                case 1: agregarVehiculo(); break;
                case 2: gestor.listarTodos(); break;
                case 3: filtrarPorTipo(); break;
                case 4: gestor.listarElectricos(); break;
                case 5: gestor.cargarBateriasBajas(); break;
                case 6: gestor.demostrarPolimorfismo(); break;
                case 7: gestor.mostrarEstadisticas(); break;
                case 8: 
                    System.out.println(" ¡Hasta pronto!");
                    break;
                default:
                    System.out.println(" Opción inválida");
            }
            
            if (opcion != 8) {
                System.out.println("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }
            
        } while (opcion != 8);
        
        scanner.close();
    }
    
    public static void mostrarMenu() {
        System.out.println("\n==========================================");
        System.out.println("      GESTIÓN DE VEHÍCULOS ");
        System.out.println("==========================================");
        System.out.println("1. Agregar vehículo");
        System.out.println("2. Listar todos");
        System.out.println("3. Filtrar por tipo");
        System.out.println("4. Ver eléctricos y batería");
        System.out.println("5. Cargar baterías bajas");
        System.out.println("6. Demostrar polimorfismo");
        System.out.println("7. Estadísticas");
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
            return 0;
        }
    }
    
    public static void agregarVehiculo() {
        System.out.println("\n--- AGREGAR VEHÍCULO ---");
        System.out.println("1. Auto");
        System.out.println("2. Moto");
        System.out.println("3. Camión");
        System.out.println("4. Auto Eléctrico");
        System.out.println("5. Moto Eléctrica");
        System.out.print("Seleccione tipo: ");
        
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        
        System.out.print("Año: ");
        int anio = scanner.nextInt();
        
        switch (tipo) {
            case 1:
                System.out.print("Número de puertas: ");
                int puertas = scanner.nextInt();
                gestor.agregar(new Auto(marca, modelo, anio, puertas));
                break;
            case 2:
                System.out.print("¿Tiene sidecar? (true/false): ");
                boolean sidecar = scanner.nextBoolean();
                gestor.agregar(new Moto(marca, modelo, anio, sidecar));
                break;
            case 3:
                System.out.print("Capacidad en toneladas: ");
                double ton = scanner.nextDouble();
                gestor.agregar(new Camion(marca, modelo, anio, ton));
                break;
            case 4:
                System.out.print("Número de puertas: ");
                puertas = scanner.nextInt();
                System.out.print("Nivel de batería (0-100): ");
                int bateria = scanner.nextInt();
                gestor.agregar(new AutoElectrico(marca, modelo, anio, puertas, bateria));
                break;
            case 5:
                System.out.print("¿Tiene sidecar? (true/false): ");
                sidecar = scanner.nextBoolean();
                System.out.print("Nivel de batería (0-100): ");
                bateria = scanner.nextInt();
                gestor.agregar(new MotoElectrica(marca, modelo, anio, sidecar, bateria));
                break;
            default:
                System.out.println("❌ Tipo inválido");
        }
    }
    
    public static void filtrarPorTipo() {
        System.out.println("\n--- FILTRAR POR TIPO ---");
        System.out.println("1. Autos");
        System.out.println("2. Motos");
        System.out.println("3. Camiones");
        System.out.println("4. Eléctricos");
        System.out.print("Seleccione: ");
        
        int op = scanner.nextInt();
        scanner.nextLine();
        
        switch (op) {
            case 1: gestor.listarPorTipo("auto"); break;
            case 2: gestor.listarPorTipo("moto"); break;
            case 3: gestor.listarPorTipo("camion"); break;
            case 4: gestor.listarPorTipo("electrico"); break;
            default: System.out.println(" Opción inválida");
        }
    }
}