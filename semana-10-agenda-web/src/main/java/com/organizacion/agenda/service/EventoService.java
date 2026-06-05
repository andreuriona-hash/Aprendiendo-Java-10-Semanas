package com.organizacion.agenda.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.organizacion.agenda.modelo.Evento;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoService {
    
    private static final String ARCHIVO = "eventos.json";
    private final Gson gson;
    
    public EventoService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    private List<Evento> cargar() {
        File file = new File(ARCHIVO);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (FileReader reader = new FileReader(file)) {
            Type tipoLista = new TypeToken<ArrayList<Evento>>() {}.getType();
            List<Evento> lista = gson.fromJson(reader, tipoLista);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error al cargar eventos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    private void guardarArchivo(List<Evento> eventos) {
        try (FileWriter writer = new FileWriter(ARCHIVO)) {
            gson.toJson(eventos, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar eventos: " + e.getMessage());
        }
    }
    
    public List<Evento> obtenerTodos() {
        return cargar();
    }
    
    public List<Evento> buscarPorTitulo(String texto) {
        String filtro = texto.toLowerCase();
        return cargar().stream()
                .filter(e -> e.getTitulo().toLowerCase().contains(filtro))
                .collect(Collectors.toList());
    }
    
    public void guardar(Evento evento) {
        List<Evento> lista = cargar();
        lista.removeIf(e -> e.getTitulo().equalsIgnoreCase(evento.getTitulo()) 
                && e.getFecha().equals(evento.getFecha()));
        lista.add(evento);
        guardarArchivo(lista);
    }
    
    public void eliminar(Evento evento) {
        List<Evento> lista = cargar();
        lista.removeIf(e -> e.getTitulo().equalsIgnoreCase(evento.getTitulo()) 
                && e.getFecha().equals(evento.getFecha()));
        guardarArchivo(lista);
    }
}