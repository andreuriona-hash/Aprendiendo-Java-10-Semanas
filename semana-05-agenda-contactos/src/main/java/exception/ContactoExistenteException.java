package exception;

public class ContactoExistenteException extends Exception {
    private String idDuplicado;
    
    public ContactoExistenteException(String idDuplicado){
        super("Ya existe un contacto con ID: " + idDuplicado);
        this.idDuplicado = idDuplicado;
    }
    public String getIdDuplicado(){
        return idDuplicado;
    }
    
}
