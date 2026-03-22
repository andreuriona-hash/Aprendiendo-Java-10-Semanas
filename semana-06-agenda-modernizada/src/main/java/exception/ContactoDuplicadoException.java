package exception;

public class ContactoDuplicadoException extends Exception {
    public ContactoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}