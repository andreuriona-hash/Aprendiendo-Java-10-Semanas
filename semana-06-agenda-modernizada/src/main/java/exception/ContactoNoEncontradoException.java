package exception;

public class ContactoNoEncontradoException extends Exception {
    private String idBuscado;

    public ContactoNoEncontradoException(String idBuscado) {
        super("No se encontró contacto con ID: " + idBuscado);
        this.idBuscado = idBuscado;
    }

    public String getIdBuscado() {
        return idBuscado;
    }
    
}
