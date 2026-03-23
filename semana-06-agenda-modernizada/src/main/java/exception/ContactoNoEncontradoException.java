package exception;

public class ContactoNoEncontradoException extends Exception {
    public ContactoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}