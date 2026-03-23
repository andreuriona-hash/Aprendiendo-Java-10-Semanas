package exception;

public class DatoInvalidoException extends RuntimeException {
    public DatoInvalidoException(String campo, String mensaje) {
        super("Dato inválido en '" + campo + "': " + mensaje);
    }
}