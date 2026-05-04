package com.organizacion.agenda.service;

import com.organizacion.agenda.modelo.Evento;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoService {
    private final ManejadorJSON<Evento> manejador;
    private static final String ARCHIVO = "eventos.json";
    
    public EventoService() {
        this.manejador = new ManejadorJSON<>(ARCHIVO);
    }
    
    public List<Evento> obtenerTodos() {
        return manejador.cargar();
    }
    
    public List<Evento> buscarPorTitulo(String texto) {
        String filtro = texto.toLowerCase();
        return manejador.cargar().stream()
                .filter(e -> e.getTitulo().toLowerCase().contains(filtro))
                .collect(Collectors.toList());
    }
    
    public void guardar(Evento evento) {
        List<Evento> lista = manejador.cargar();
        lista.removeIf(e -> e.getTitulo().equalsIgnoreCase(evento.getTitulo()) 
                && e.getFecha().equals(evento.getFecha()));
        lista.add(evento);
        manejador.guardar(lista);
    }
    
    public void eliminar(Evento evento) {
        List<Evento> lista = manejador.cargar();
        lista.removeIf(e -> e.getTitulo().equalsIgnoreCase(evento.getTitulo()) 
                && e.getFecha().equals(evento.getFecha()));
        manejador.guardar(lista);
    }
}
