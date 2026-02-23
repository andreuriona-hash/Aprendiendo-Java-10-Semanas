package servicio;

import modelo.Tarea;
import java.util.ArrayList;

public class GestorTareas {
    // Atributos
    private ArrayList<Tarea> tareas;
    private int proximoId;
    
    // Constructor
    public GestorTareas() {
        this.tareas = new ArrayList<>();
        this.proximoId = 1; // El primer ID será 1
    }
    
    // RF2: Agregar tarea
    public void agregar(String titulo, String descripcion) {
        Tarea nueva = new Tarea(proximoId, titulo, descripcion);
        tareas.add(nueva);
        System.out.println(" Tarea agregada con ID: " + proximoId);
        proximoId++;
    }
    
    // RF3: Listar todas
    public void listarTodas() {
        if (tareas.isEmpty()) {
            System.out.println("📭 No hay tareas registradas");
            return;
        }
        
        System.out.println("\n=== LISTA DE TODAS LAS TAREAS ===");
        for (Tarea t : tareas) {
            t.mostrarInfo();
        }
    }
    
    // RF4: Listar pendientes
    public void listarPendientes() {
        boolean hayPendientes = false;
        
        System.out.println("\n=== TAREAS PENDIENTES ===");
        for (Tarea t : tareas) {
            if (!t.estaCompletada()) {
                t.mostrarInfo();
                hayPendientes = true;
            }
        }
        
        if (!hayPendientes) {
            System.out.println(" No hay tareas pendientes");
        }
    }
    
    // RF4: Listar completadas
    public void listarCompletadas() {
        boolean hayCompletadas = false;
        
        System.out.println("\n=== TAREAS COMPLETADAS ===");
        for (Tarea t : tareas) {
            if (t.estaCompletada()) {
                t.mostrarInfo();
                hayCompletadas = true;
            }
        }
        
        if (!hayCompletadas) {
            System.out.println("📝 No hay tareas completadas aún");
        }
    }
    
    // RF5: Marcar como completada
    public void completarTarea(int id) {
        Tarea tarea = buscarPorId(id);
        
        if (tarea == null) {
            System.out.println(" No existe tarea con ID: " + id);
            return;
        }
        
        if (tarea.estaCompletada()) {
            System.out.println(" La tarea ya estaba completada");
        } else {
            tarea.completar();
        }
    }
    
    // RF6: Eliminar tarea
    public void eliminarTarea(int id) {
        Tarea tarea = buscarPorId(id);
        
        if (tarea == null) {
            System.out.println(" No existe tarea con ID: " + id);
            return;
        }
        
        tareas.remove(tarea);
        System.out.println(" Tarea eliminada correctamente");
    }
    
    // Método auxiliar: buscar por ID
    private Tarea buscarPorId(int id) {
        for (Tarea t : tareas) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null; // No encontrado
    }
    
    // RF7: Estadísticas
    public void mostrarEstadisticas() {
        int total = tareas.size();
        int pendientes = 0;
        int completadas = 0;
        
        for (Tarea t : tareas) {
            if (t.estaCompletada()) {
                completadas++;
            } else {
                pendientes++;
            }
        }
        
        System.out.println("\n=== ESTADÍSTICAS ===");
        System.out.println(" Total de tareas: " + total);
        System.out.println(" Pendientes: " + pendientes);
        System.out.println(" Completadas: " + completadas);
        
        if (total > 0) {
            double porcentaje = (completadas * 100.0) / total;
            System.out.printf("📈 Progreso: %.1f%% completado\n", porcentaje);
        }
    }
    
    // Getters adicionales (útiles para el menú)
    public int getTotalTareas() {
        return tareas.size();
    }
    
    public boolean hayTareas() {
        return !tareas.isEmpty();
    }
}