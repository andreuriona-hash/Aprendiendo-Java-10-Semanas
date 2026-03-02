package modelo;

public abstract class Vehiculo {
    protected String marca;
    protected String modelo;
    protected int anio;
    
    public Vehiculo(String marca, String modelo, int anio) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }
    
    public abstract void acelerar();
    
    public String getInfo() {
        return marca + " " + modelo + " (" + anio + ")";
    }
    
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAnio() { return anio; }
}