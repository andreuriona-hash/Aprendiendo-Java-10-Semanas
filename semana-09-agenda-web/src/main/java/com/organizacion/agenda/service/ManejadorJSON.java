package com.organizacion.agenda.service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.organizacion.agenda.modelo.Contacto;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ManejadorJSON {
    private final String archivo;
    private final Gson gson;
    
    public ManejadorJSON(String archivo) {
        this.archivo = archivo;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    public List<Contacto> cargar() {
        File file = new File(archivo);
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
    
    public void guardar(List<Contacto> contactos) {
        try (FileWriter writer = new FileWriter(archivo)) {
            gson.toJson(contactos, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar contactos: " + e.getMessage());
        }
    }
    
}
