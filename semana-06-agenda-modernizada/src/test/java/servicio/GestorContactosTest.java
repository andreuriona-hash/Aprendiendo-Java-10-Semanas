package servicio;

import modelo.Contacto;
import exception.ContactoDuplicadoException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GestorContactosTest {
    
    private GestorContactos gestor;
    private String archivoTest = "datos/test_contactos.json";
    private String backupTest = "datos/test_backup.json";
    
    @BeforeEach
    void setUp() {
        new File(archivoTest).delete();
        new File(backupTest).delete();
        
        gestor = new GestorContactos(archivoTest, backupTest);
        
        try {
            gestor.agregarContacto(new Contacto("Ana Lopez", "76543210", "ana@email.com", "Amigos", "Calle 1"));
            gestor.agregarContacto(new Contacto("Carlos Rios", "71112233", "carlos@email.com", "Trabajo", "Calle 2"));
            gestor.agregarContacto(new Contacto("Beatriz Vega", "68881234", "beatriz@email.com", "Trabajo", "Calle 3"));
            gestor.agregarContacto(new Contacto("David Cruz", "72223344", "david@email.com", "Familia", "Calle 4"));
        } catch (ContactoDuplicadoException e) {
            fail("SetUp no deberia lanzar excepcion: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Buscar por nombre encuentra contacto existente")
    void buscarPorNombreEncuentraContactoExistente() {
        Optional<Contacto> resultado = gestor.buscarPorNombre("Ana Lopez");
        assertTrue(resultado.isPresent());
        assertEquals("76543210", resultado.get().getTelefono());
    }
    
    @Test
    @DisplayName("Buscar por nombre retorna vacio si no existe")
    void buscarPorNombreRetornaVacioSiNoExiste() {
        Optional<Contacto> resultado = gestor.buscarPorNombre("Nombre Inexistente");
        assertFalse(resultado.isPresent());
    }
    
    @Test
    @DisplayName("Filtrar por categoria devuelve solo los correctos")
    void filtrarPorCategoriaDevuelveSoloCorrectos() {
        List<Contacto> trabajo = gestor.filtrarPorCategoria("Trabajo");
        assertEquals(2, trabajo.size());
        assertTrue(trabajo.stream().allMatch(c -> c.getCategoria().equalsIgnoreCase("Trabajo")));
    }
    
    @Test
    @DisplayName("Filtrar por categoria inexistente devuelve lista vacia")
    void filtrarPorCategoriaInexistenteDevuelveVacia() {
        List<Contacto> resultado = gestor.filtrarPorCategoria("Inexistente");
        assertTrue(resultado.isEmpty());
    }
    
    @Test
    @DisplayName("Obtener nombres devuelve todos los nombres")
    void obtenerNombresDevuelveTodosLosNombres() {
        List<String> nombres = gestor.obtenerNombres();
        assertEquals(4, nombres.size());
        assertTrue(nombres.contains("Ana Lopez"));
        assertTrue(nombres.contains("Carlos Rios"));
    }
    
    @Test
    @DisplayName("Contar por categoria retorna cantidad correcta")
    void contarPorCategoriaRetornaCantidadCorrecta() {
        long totalTrabajo = gestor.contarPorCategoria("Trabajo");
        long totalFamilia = gestor.contarPorCategoria("Familia");
        assertEquals(2, totalTrabajo);
        assertEquals(1, totalFamilia);
    }
    
    @Test
    @DisplayName("Listar ordenados devuelve orden alfabetico")
    void listarOrdenadosDevuelveOrdenAlfabetico() {
        List<Contacto> ordenados = gestor.listarOrdenados();
        assertEquals(4, ordenados.size());
        assertEquals("Ana Lopez", ordenados.get(0).getNombre());
        assertEquals("Beatriz Vega", ordenados.get(1).getNombre());
        assertEquals("Carlos Rios", ordenados.get(2).getNombre());
        assertEquals("David Cruz", ordenados.get(3).getNombre());
    }
    
    @Test
    @DisplayName("Agregar contacto duplicado lanza excepcion")
    void agregarContactoDuplicadoLanzaExcepcion() {
        Contacto duplicado = new Contacto("Ana Lopez", "99999999", "otro@email.com", "Amigos", "");
        assertThrows(ContactoDuplicadoException.class, () -> {
            gestor.agregarContacto(duplicado);
        });
    }
}