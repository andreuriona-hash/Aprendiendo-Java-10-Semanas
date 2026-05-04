package com.organizacion.agenda.service;

import com.organizacion.agenda.modelo.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventoServiceTest {

    private EventoService service;

    @BeforeEach
    void setUp() {
        service = new EventoService();
    }

    @Test
    void testGuardarYObtener() {
        Evento e = new Evento("Reunion", "2026-05-15", "Revisión del proyecto");
        service.guardar(e);
        assertTrue(service.obtenerTodos().stream().anyMatch(ev -> ev.getTitulo().equals("Reunion")));
    }

    @Test
    void testEliminar() {
        Evento e = new Evento("Eliminar", "2026-05-15", "Test eliminar");
        service.guardar(e);
        service.eliminar(e);
        assertFalse(service.obtenerTodos().stream().anyMatch(ev -> ev.getTitulo().equals("Eliminar")));
    }
}