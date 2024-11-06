package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.util.UtilesArchivo;

class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    public void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
        restaurante.iniciarPedido("Juan", "Calle 123");
        assertNotNull(restaurante.getPedidoEnCurso());
        assertEquals("Juan", restaurante.getPedidoEnCurso().getNombreCliente());
    }

    @Test
    public void testIniciarPedidoConPedidoEnCurso() {
        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
            restaurante.iniciarPedido("Juan", "Calle 123");
            restaurante.iniciarPedido("Maria", "Calle 456");
        });
    }

    @Test
    public void testCerrarYGuardarPedido(@TempDir File tempDir) throws YaHayUnPedidoEnCursoException, IOException, NoHayPedidoEnCursoException {
        restaurante.iniciarPedido("Juan", "Calle 123");
        File file = new File(tempDir, "factura_0.txt");

        restaurante.cerrarYGuardarPedido();

        assertTrue(file.exists());
    }

    @Test
    public void testCerrarPedidoSinPedidoEnCurso() {
        assertThrows(NoHayPedidoEnCursoException.class, () -> restaurante.cerrarYGuardarPedido());
    }

    @Test
    public void testCargarIngredientes(@TempDir File tempDir) throws IOException, HamburguesaException {
        File archivoIngredientes = new File(tempDir, "ingredientes.txt");
        UtilesArchivo.crearArchivo(archivoIngredientes, "Lechuga;1000\nTomate;800\nQueso Extra;2000\nTocineta;3000");

        restaurante.cargarInformacionRestaurante(archivoIngredientes, null, null);

        assertEquals(4, restaurante.getIngredientes().size());
    }

    @Test
    public void testCargarIngredientesConRepetidos(@TempDir File tempDir) throws IOException {
        File archivoIngredientes = new File(tempDir, "ingredientes.txt");
        UtilesArchivo.crearArchivo(archivoIngredientes, "Lechuga;1000\nTomate;800\nLechuga;1000");

        assertThrows(IngredienteRepetidoException.class, () -> restaurante.cargarInformacionRestaurante(archivoIngredientes, null, null));
    }

    @Test
    public void testCargarMenu(@TempDir File tempDir) throws IOException, HamburguesaException {
        File archivoMenu = new File(tempDir, "menuBase.txt");
        UtilesArchivo.crearArchivo(archivoMenu, "Hamburguesa;15000");

        restaurante.cargarInformacionRestaurante(null, archivoMenu, null);

        assertEquals(1, restaurante.getMenuBase().size());
    }

    @Test
    public void testCargarCombos(@TempDir File tempDir) throws IOException, HamburguesaException {
        File archivoCombos = new File(tempDir, "combos.txt");
        UtilesArchivo.crearArchivo(archivoCombos, "Combo1;10% ;Hamburguesa");

        restaurante.cargarInformacionRestaurante(null, null, archivoCombos);

        assertEquals(1, restaurante.getMenuCombos().size());
    }

    @Test
    public void testProductoFaltanteEnCombo(@TempDir File tempDir) throws IOException {
        File archivoCombos = new File(tempDir, "combos.txt");
        UtilesArchivo.crearArchivo(archivoCombos, "Combo1;10% ;Hamburguesa;Papas");

        assertThrows(ProductoFaltanteException.class, () -> restaurante.cargarInformacionRestaurante(null, null, archivoCombos));
    }

    @Test
    public void testProductoRepetidoEnMenu(@TempDir File tempDir) throws IOException {
        File archivoMenu = new File(tempDir, "menuBase.txt");
        UtilesArchivo.crearArchivo(archivoMenu, "Hamburguesa;15000\nHamburguesa;15000");

        assertThrows(ProductoRepetidoException.class, () -> restaurante.cargarInformacionRestaurante(null, archivoMenu, null));
    }

}