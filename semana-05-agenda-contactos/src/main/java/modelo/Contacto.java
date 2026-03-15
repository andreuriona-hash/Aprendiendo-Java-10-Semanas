package modelo;

import exception.DatoInvalidoException;

public class Contacto {
    private String id;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;
    
    public Contacto(String nombre, String telefono, String email, String direccion) {
        setNombre(nombre);
        setTelefono(telefono);
        setEmail(email);
        setDireccion(direccion);
    }
    
    public Contacto(String id, String nombre, String telefono, String email, String direccion) {
        this.id = id;
        setNombre(nombre);
        setTelefono(telefono);
        setEmail(email);
        setDireccion(direccion);
    }
    
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public String getDireccion() { return direccion; }
    
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatoInvalidoException("nombre", "No puede estar vacío");
        }
        this.nombre = nombre.trim();
    }
    
    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new DatoInvalidoException("teléfono", "No puede estar vacío");
        }
        String tel = telefono.trim();
        if (tel.length() < 7 || tel.length() > 8) {
            throw new DatoInvalidoException("teléfono", "Debe tener entre 7 y 8 dígitos");
        }
        this.telefono = tel;
    }
    
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new DatoInvalidoException("email", "No puede estar vacío");
        }
        String correo = email.trim();
        if (!correo.contains("@")) {
            throw new DatoInvalidoException("email", "Debe contener @");
        }
        this.email = correo;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = (direccion == null) ? "" : direccion.trim();
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s | Tel: %s | Email: %s", 
                id, nombre, telefono, email);
    }
    
    public String toStringDetalle() {
        return String.format("ID: %s\nNombre: %s\nTeléfono: %s\nEmail: %s\nDirección: %s",
                id, nombre, telefono, email, 
                direccion.isEmpty() ? "No especificada" : direccion);
    }
}