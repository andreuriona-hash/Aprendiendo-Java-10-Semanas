package com.organizacion.agenda.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ManejadorJSON<T> {
    
    private final String archivo;
    private final Gson gson;
    
    public ManejadorJSON(String archivo) {
        this.archivo = archivo;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    public List<T> cargar() {
        File file = new File(archivo);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (FileReader reader = new FileReader(file)) {
            Type tipoLista = new TypeToken<ArrayList<T>>() {}.getType();
            List<T> lista = gson.fromJson(reader, tipoLista);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public void guardar(List<T> datos) {
        try (FileWriter writer = new FileWriter(archivo)) {
            gson.toJson(datos, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }
}