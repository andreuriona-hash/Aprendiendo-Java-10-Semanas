package servicio;
import modelo.Contacto;
import exception.ContactoDuplicadoException;
import util.ManejadorJSON;

import java.util.*;
import java.util.stream.Collectors;

public class GestorContactos {
    private static final String ARCHIVO = "datos/contactos.json";
    private static final String BACKUP = "datos/contactos.backup.json";
    
    private List<Contacto> contactos;
    private int nextId;
    
    public GestorContactos() {
        this.contactos = ManejadorJSON.cargar(ARCHIVO);
        calcularSiguienteId();
        System.out.println("Agenda cargada con " + contactos.size() + " contactos.");
    }
    
    private void calcularSiguienteId() {
        nextId = 1;
        for (Contacto c : contactos) {
            String id = c.getId();
            if (id != null && id.startsWith("C")) {
                try {
                    int num = Integer.parseInt(id.substring(1));
                    if (num >= nextId) {
                        nextId = num + 1;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
    }
    
    private String generarId() {
        return String.format("C%03d", nextId++);
    }
    
    private void persistir() {
        ManejadorJSON.guardarConBackup(new ArrayList<>(contactos), ARCHIVO, BACKUP);
    }
    
    public void agregarContacto(Contacto contacto) throws ContactoDuplicadoException {
        boolean existe = contactos.stream()
                .anyMatch(c -> c.getNombre().equalsIgnoreCase(contacto.getNombre()));
        
        if (existe) {
            throw new ContactoDuplicadoException("Ya existe un contacto con el nombre: " + contacto.getNombre());
        }
        
        String id = generarId();
        contacto.setId(id);
        contactos.add(contacto);
        persistir();
    }
    
    public Optional<Contacto> buscarPorNombre(String nombre) {
        return contactos.stream()
                .filter(c -> c.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }
    
    public List<Contacto> filtrarPorCategoria(String categoria) {
        return contactos.stream()
                .filter(c -> c.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }
    
    public List<String> obtenerNombres() {
        return contactos.stream()
                .map(Contacto::getNombre)
                .collect(Collectors.toList());
    }
    
    public long contarPorCategoria(String categoria) {
        return contactos.stream()
                .filter(c -> c.getCategoria().equalsIgnoreCase(categoria))
                .count();
    }
    
    public List<Contacto> listarOrdenados() {
        return contactos.stream()
                .sorted(Comparator.comparing(Contacto::getNombre))
                .collect(Collectors.toList());
    }
    
    public List<Contacto> obtenerTodos() {
        return new ArrayList<>(contactos);
    }
    
    public int totalContactos() {
        return contactos.size();
    }
    
    public void mostrarEstadisticas() {
        System.out.println("\n=== ESTADISTICAS ===");
        System.out.println("Total contactos: " + totalContactos());
        
        long conEmail = contactos.stream()
                .filter(c -> c.getEmail() != null && !c.getEmail().isEmpty())
                .count();
        System.out.println("Contactos con email: " + conEmail);
        
        Map<String, Long> porCategoria = contactos.stream()
                .collect(Collectors.groupingBy(Contacto::getCategoria, Collectors.counting()));
        
        System.out.println("\nContactos por categoria:");
        porCategoria.forEach((cat, count) -> System.out.println("  " + cat + ": " + count));
    }
}
