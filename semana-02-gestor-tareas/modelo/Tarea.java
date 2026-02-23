package modelo;

public class Tarea {
    // Atributos privados (encapsulamiento)
    private int id;
    private String titulo;
    private String descripcion;
    private String estado; // "PENDIENTE" o "COMPLETADA"
    
    // Constructor
    public Tarea(int id, String titulo, String descripcion) {
        this.id = id;
        setTitulo(titulo);       // Usamos setter para validar
        setDescripcion(descripcion);
        this.estado = "PENDIENTE"; // Por defecto
    }
    
    // Getters (para leer valores)
    public int getId() {
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public String getEstado() {
        return estado;
    }
    
    // Setters con validaciones
    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty()) {
            this.titulo = titulo;
        } else {
            System.out.println("ERROR: El título no puede estar vacío");
            this.titulo = "SIN TÍTULO";
        }
    }
    
    public void setDescripcion(String descripcion) {
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            this.descripcion = descripcion;
        } else {
            System.out.println("ERROR: La descripción no puede estar vacía");
            this.descripcion = "SIN DESCRIPCIÓN";
        }
    }
    
    // Métodos de negocio
    public void completar() {
        if (this.estado.equals("PENDIENTE")) {
            this.estado = "COMPLETADA";
            System.out.println("Tarea completada: " + titulo);
        } else {
            System.out.println("La tarea ya estaba completada");
        }
    }
    
    public boolean estaCompletada() {
        return this.estado.equals("COMPLETADA");
    }
    
    public void mostrarInfo() {
        System.out.println("------------------------");
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Estado: " + estado);
        System.out.println("------------------------");
    }
}