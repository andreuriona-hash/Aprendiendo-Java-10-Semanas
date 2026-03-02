package modelo;

public interface Electrico {
    void cargarBateria();
    int getNivelBateria();
    boolean necesitaCarga(); //  si la batería < 20%
}