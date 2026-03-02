package modelo;

public class Camion extends Vehiculo {
    private double capacidadToneladas;
    
    public Camion(String marca, String modelo, int anio, double capacidadToneladas) {
        super(marca, modelo, anio);
        this.capacidadToneladas = capacidadToneladas;
    }
    
    @Override
    public void acelerar() {
        System.out.println(marca + " (camión de " + capacidadToneladas + "t) acelera lentamente.");
    }
    
    public double getCapacidadToneladas() {
        return capacidadToneladas;
    }
}