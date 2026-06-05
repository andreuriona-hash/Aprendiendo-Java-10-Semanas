package com.organizacion.agenda.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.organizacion.agenda.modelo.Contacto;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactoService {
    
    private static final String ARCHIVO = "contactos.json";
    private final Gson gson;
    
    public ContactoService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    private List<Contacto> cargar() {
        File file = new File(ARCHIVO);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (FileReader reader = new FileReader(file)) {
            Type tipoLista = new TypeToken<ArrayList<Contacto>>() {}.getType();
            List<Contacto> lista = gson.fromJson(reader, tipoLista);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error al cargar contactos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    private void guardarArchivo(List<Contacto> contactos) {
        try (FileWriter writer = new FileWriter(ARCHIVO)) {
            gson.toJson(contactos, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar contactos: " + e.getMessage());
        }
    }
    
    public List<Contacto> obtenerTodos() {
        return cargar();
    }
    
    public List<Contacto> buscarPorNombre(String texto) {
        String filtro = texto.toLowerCase();
        return cargar().stream()
                .filter(c -> c.getNombre().toLowerCase().contains(filtro))
                .collect(Collectors.toList());
    }
    
    public void guardar(Contacto contacto) {
        List<Contacto> lista = cargar();
        lista.removeIf(c -> c.getNombre().equalsIgnoreCase(contacto.getNombre()));
        lista.add(contacto);
        guardarArchivo(lista);
    }
    
    public void eliminar(Contacto contacto) {
        List<Contacto> lista = cargar();
        lista.removeIf(c -> c.getNombre().equalsIgnoreCase(contacto.getNombre()));
        guardarArchivo(lista);
    }
}