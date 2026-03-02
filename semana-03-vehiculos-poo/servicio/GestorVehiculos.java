package servicio;

import modelo.*;
import java.util.ArrayList;

public class GestorVehiculos {
    private ArrayList<Vehiculo> vehiculos;
    
    public GestorVehiculos() {
        this.vehiculos = new ArrayList<>();
    }
    
    // RF1: Agregar vehículo
    public void agregar(Vehiculo v) {
        vehiculos.add(v);
        System.out.println(" Agregado: " + v.getInfo());
    }
    
    // RF2: Listar toda la flota
    public void listarTodos() {
        System.out.println("\n=== FLOTA COMPLETA ===");
        if (vehiculos.isEmpty()) {
            System.out.println(" No hay vehículos registrados");
            return;
        }
        for (Vehiculo v : vehiculos) {
            System.out.println("  " + v.getInfo());
        }
    }
    
    // RF3: Filtrar por tipo
    public void listarPorTipo(String tipo) {
        System.out.println("\n=== TIPO: " + tipo.toUpperCase() + " ===");
        boolean hay = false;
        
        for (Vehiculo v : vehiculos) {
            boolean mostrar = false;
            
            if (tipo.equalsIgnoreCase("auto") && v instanceof Auto && !(v instanceof AutoElectrico))
                mostrar = true;
            else if (tipo.equalsIgnoreCase("moto") && v instanceof Moto && !(v instanceof MotoElectrica))
                mostrar = true;
            else if (tipo.equalsIgnoreCase("camion") && v instanceof Camion)
                mostrar = true;
            else if (tipo.equalsIgnoreCase("electrico") && v instanceof Electrico)
                mostrar = true;
            
            if (mostrar) {
                System.out.println("  " + v.getInfo());
                hay = true;
            }
        }
        
        if (!hay) System.out.println("  No hay vehículos de este tipo");
    }
    
    // RF4: Mostrar eléctricos con batería
    public void listarElectricos() {
        System.out.println("\n=== VEHÍCULOS ELÉCTRICOS ===");
        boolean hay = false;
        
        for (Vehiculo v : vehiculos) {
            if (v instanceof Electrico) {
                Electrico e = (Electrico) v;
                System.out.println("  " + v.getInfo() + " | Batería: " + e.getNivelBateria() + "%");
                hay = true;
            }
        }
        
        if (!hay) System.out.println("  No hay vehículos eléctricos");
    }
    
    // RF5: Cargar baterías bajas
    public void cargarBateriasBajas() {
        System.out.println("\n=== CARGANDO BATERÍAS BAJAS (<20%) ===");
        boolean hay = false;
        
        for (Vehiculo v : vehiculos) {
            if (v instanceof Electrico) {
                Electrico e = (Electrico) v;
                if (e.necesitaCarga()) {
                    System.out.print("  " + v.getInfo() + " → ");
                    e.cargarBateria();
                    hay = true;
                }
            }
        }
        
        if (!hay) System.out.println("  Todos los eléctricos tienen carga suficiente");
    }
    
    // RF6: Demostrar polimorfismo
    public void demostrarPolimorfismo() {
        System.out.println("\n=== DEMOSTRACIÓN DE POLIMORFISMO ===");
        System.out.println("Llamando a acelerar() en toda la flota:\n");
        
        for (Vehiculo v : vehiculos) {
            v.acelerar();
        }
    }
    
    // RF7: Estadísticas
    public void mostrarEstadisticas() {
        int total = vehiculos.size();
        int electricos = 0;
        int necesitanCarga = 0;
        
        for (Vehiculo v : vehiculos) {
            if (v instanceof Electrico) {
                electricos++;
                if (((Electrico) v).necesitaCarga()) {
                    necesitanCarga++;
                }
            }
        }
        
        System.out.println("\n=== ESTADÍSTICAS ===");
        System.out.println(" Total vehículos: " + total);
        System.out.println(" Eléctricos: " + electricos);
        System.out.println(" Necesitan carga: " + necesitanCarga);
    }
    
    public boolean hayVehiculos() {
        return !vehiculos.isEmpty();
    }
}    