package modelo;

public class MotoElectrica extends Moto implements Electrico {
    private int nivelBateria;
    
    public MotoElectrica(String marca, String modelo, int anio, boolean tieneSidecar, int nivelBateria) {
        super(marca, modelo, anio, tieneSidecar);
        this.nivelBateria = nivelBateria;
    }
    
    @Override
    public void cargarBateria() {
        nivelBateria = 100;
        System.out.println(marca + " (moto eléctrica) batería cargada al 100%");
    }
    
    @Override
    public int getNivelBateria() {
        return nivelBateria;
    }
    
    @Override
    public boolean necesitaCarga() {
        return nivelBateria < 20;
    }
    
    @Override
    public void acelerar() {
        if (nivelBateria > 0) {
            nivelBateria -= 3;
            System.out.println(marca + " (moto eléctrica) acelera sin ruido. Batería: " + nivelBateria + "%");
        } else {
            System.out.println(marca + " (moto eléctrica) sin batería, no puede acelerar");
        }
    }
}