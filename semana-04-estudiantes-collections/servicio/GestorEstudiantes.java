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

    public void mostrarRanking() {
    System.out.println("\n=== RANKING DE ESTUDIANTES (menor a mayor promedio) ===");
    
    if (ranking.isEmpty()) {
        System.out.println("No hay estudiantes registrados.");
        return;
    }
    
    int posicion = 1;
    for (Estudiante e : ranking) {
        System.out.printf("%3d. %s\n", posicion++, e);
         }
    }

    public void mostrarTop5() {
    System.out.println("\n=== TOP 5 ESTUDIANTES (mejores promedios) ===");
    
    if (ranking.isEmpty()) {
        System.out.println("No hay estudiantes registrados.");
        return;
    }
    
    ArrayList<Estudiante> lista = new ArrayList<>(ranking);
    
    int total = lista.size();
    int limite = Math.min(5, total); 
    
    for (int i = 0; i < limite; i++) {
 Estudiante e = lista.get(total - 1 - i);
        System.out.printf("%3d. %s\n", i + 1, e);
       }
    }

    public void filtrarPorCarrera(String carreraBuscada) {
    System.out.println("\n=== ESTUDIANTES DE " + carreraBuscada.toUpperCase() + " ===");
    
    boolean encontrado = false;
    
    for (Estudiante e : ranking) {
        if (e.getCarrera().equalsIgnoreCase(carreraBuscada)) {
            System.out.println("  " + e);
            encontrado = true;
        }
    }
    
    if (!encontrado) {
        System.out.println("No hay estudiantes de la carrera: " + carreraBuscada);
       }
    }

    public void mostrarEstadisticas() {
    System.out.println("\n=== ESTADÍSTICAS GENERALES ===");
    
    int total = porCarnet.size();
    if (total == 0) {
        System.out.println("No hay estudiantes registrados.");
        return;
    }
    
    double suma = 0;
    double max = Double.MIN_VALUE;
    double min = Double.MAX_VALUE;
    String nombreMax = "", nombreMin = "";
    
    for (Estudiante e : porCarnet.values()) {
        double prom = e.getPromedio();
        suma += prom;
        
        if (prom > max) {
            max = prom;
            nombreMax = e.getNombre();
        }
        
        if (prom < min) {
            min = prom;
            nombreMin = e.getNombre();
        }
    }
    
    double promedioGeneral = suma / total;
    
    System.out.println(" Total estudiantes: " + total);
    System.out.printf(" Promedio general: %.2f\n", promedioGeneral);
    System.out.printf(" Mejor promedio: %.2f (%s)\n", max, nombreMax);
    System.out.printf(" Peor promedio: %.2f (%s)\n", min, nombreMin);
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
