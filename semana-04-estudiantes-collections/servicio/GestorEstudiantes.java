package servicio;

import modelo.Estudiante;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.ArrayList;

public class GestorEstudiantes {
    
    private HashMap<String, Estudiante> porCarnet;  
    private TreeSet<Estudiante> ranking;           
    private ArrayList<String> historial;           
    
    public GestorEstudiantes() {
        this.porCarnet = new HashMap<>();
        this.ranking = new TreeSet<>();
        this.historial = new ArrayList<>();
    }
    
    public boolean agregar(Estudiante e) {
        if (porCarnet.containsKey(e.getCarnet())) {
            return false; 
        }
        
        porCarnet.put(e.getCarnet(), e);
        ranking.add(e);
        historial.add("AGREGAR: " + e.getCarnet() + " - " + e.getNombre());
        
        return true;
    }
    
    public Estudiante buscar(String carnet) {
        return porCarnet.get(carnet); 
    }

    public boolean eliminar(String carnet){
        Estudiante e = porCarnet.remove(carnet);
        if (e == null) return false;
        ranking.remove(e);
        historial.add("ELIMINAR:" + carnet + "-" + e.getNombre());
        return true;
    }

    public boolean actualizarPromedio(String carnet, double nuevoPromedio){
        Estudiante e = porCarnet.get(carnet);
        if (e == null) return false;
        ranking.remove(e);
        e.setPromedio(nuevoPromedio);
        ranking.add(e);
        historial.add("ACTUALIZAR:" + carnet + "nuevo promedio:" + nuevoPromedio);
        return true;
    }
    
    public boolean existeCarnet(String carnet) {
        return porCarnet.containsKey(carnet);
    }
    
    public int getTotalEstudiantes() {
        return porCarnet.size();
    }
    
    public void mostrarHistorial() {
        System.out.println("\n=== HISTORIAL DE OPERACIONES ===");
        if (historial.isEmpty()) {
            System.out.println("No hay operaciones registradas.");
            return;
        }
        for (String operacion : historial) {
            System.out.println("  " + operacion);
        }
    }
}
