package servicio;

import modelo.Contacto;
import exception.ContactoExistenteException;
import exception.ContactoNoEncontradoException;
import util.ManejadorJSON;

import java.util.ArrayList;

public class AgendaContactos {
    
    private static final String ARCHIVO = "data/contactos.json";
    private static final String BACKUP = "data/contactos.backup.json";
    
    private ArrayList<Contacto> contactos;
    private int nextId;
    
    public AgendaContactos() {
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
        ManejadorJSON.guardarConBackup(contactos, ARCHIVO, BACKUP);
    }
    
    public void agregar(String nombre, String telefono, String email, String direccion) 
            throws ContactoExistenteException {
        
        String id = generarId();
        Contacto nuevo = new Contacto(id, nombre, telefono, email, direccion);
        contactos.add(nuevo);
        persistir();
    }
    
    public Contacto buscarPorId(String id) throws ContactoNoEncontradoException {
        for (Contacto c : contactos) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        throw new ContactoNoEncontradoException(id);
    }
    
    public ArrayList<Contacto> buscarPorNombre(String fragmento) {
        ArrayList<Contacto> resultados = new ArrayList<>();
        String busqueda = fragmento.toLowerCase();
        
        for (Contacto c : contactos) {
            if (c.getNombre().toLowerCase().contains(busqueda)) {
                resultados.add(c);
            }
        }
        return resultados;
    }
    
    public void editar(String id, String nuevoTelefono, String nuevoEmail) 
            throws ContactoNoEncontradoException {
        Contacto c = buscarPorId(id);
        if (nuevoTelefono != null && !nuevoTelefono.isEmpty()) {
            c.setTelefono(nuevoTelefono);
        }
        if (nuevoEmail != null && !nuevoEmail.isEmpty()) {
            c.setEmail(nuevoEmail);
        }
        persistir();
    }
    
    public void eliminar(String id) throws ContactoNoEncontradoException {
        Contacto c = buscarPorId(id);
        contactos.remove(c);
        persistir();
    }
    
    public ArrayList<Contacto> listarTodos() {
        return new ArrayList<>(contactos);
    }
    
    public int total() {
        return contactos.size();
    }
    
    public int totalConEmail() {
        int count = 0;
        for (Contacto c : contactos) {
            if (c.getEmail() != null && !c.getEmail().isEmpty()) {
                count++;
            }
        }
        return count;
    }
    
    public void mostrarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS ===");
        System.out.println("Total de contactos: " + total());
        System.out.println("Contactos con email: " + totalConEmail());
    }
}