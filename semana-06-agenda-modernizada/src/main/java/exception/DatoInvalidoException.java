package exception;

public class DatoInvalidoException extends RuntimeException {
    private String campo;

    public DatoInvalidoException(String campo, String mensaje) {
        super("Dato inválido en '" + campo + "': " + mensaje);
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }
    
}
