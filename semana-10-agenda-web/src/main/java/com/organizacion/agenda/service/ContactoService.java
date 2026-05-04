package com.organizacion.agenda.service;

import com.organizacion.agenda.modelo.Contacto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactoService {
    private final ManejadorJSON manejador;
    private static final String ARCHIVO = "contactos.json";
    
    public ContactoService() {
        this.manejador = new ManejadorJSON(ARCHIVO);
    }
    
    public List<Contacto> obtenerTodos() {
        return manejador.cargar();
    }
    
    public List<Contacto> buscarPorNombre(String texto) {
        String filtro = texto.toLowerCase();
        return manejador.cargar().stream()
                .filter(c -> c.getNombre().toLowerCase().contains(filtro))
                .collect(Collectors.toList());
    }
    
    public void guardar(Contacto contacto) {
        List<Contacto> lista = manejador.cargar();
        lista.removeIf(c -> c.getNombre().equalsIgnoreCase(contacto.getNombre()));
        lista.add(contacto);
        manejador.guardar(lista);
    }
    
    public void eliminar(Contacto contacto) {
        List<Contacto> lista = manejador.cargar();
        lista.removeIf(c -> c.getNombre().equalsIgnoreCase(contacto.getNombre()));
        manejador.guardar(lista);
    }
    
}
//