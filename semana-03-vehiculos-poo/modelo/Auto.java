package modelo;

public class Auto extends Vehiculo {
    private int numeroPuertas;
    
    public Auto(String marca, String modelo, int anio, int numeroPuertas) {
        super(marca, modelo, anio);
        this.numeroPuertas = numeroPuertas;
    }
    
    @Override
    public void acelerar() {
        System.out.println(marca + " (auto) acelera a velocidad moderada.");
    }
    
    public int getNumeroPuertas() {
        return numeroPuertas;
    }
}