package modelo;

public class Estudiante implements Comparable<Estudiante> {
    private String carnet;
    private String nombre;
    private String carrera;
    private double promedio;
    
    public Estudiante(String carnet, String nombre, String carrera, double promedio) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.carrera = carrera;
        this.promedio = promedio;
    }
    
    // De menor a mayor promedio, con desempate por carnet
    @Override
    public int compareTo(Estudiante otro) {
        int cmp = Double.compare(this.promedio, otro.promedio);
        if (cmp == 0) {
            return this.carnet.compareTo(otro.carnet); // DESEMPATE
        }
        return cmp;
    }
    
    // Getters
    public String getCarnet() { return carnet; }
    public String getNombre() { return nombre; }
    public String getCarrera() { return carrera; }
    public double getPromedio() { return promedio; }
    
    // Setter con validación
    public void setPromedio(double promedio) {
        if (promedio >= 0 && promedio <= 100) {
            this.promedio = promedio;
        } else {
            System.out.println("ERROR: El promedio debe estar entre 0 y 100");
        }
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %-20s %-15s %.1f",
                carnet, nombre, carrera, promedio);
    }
}