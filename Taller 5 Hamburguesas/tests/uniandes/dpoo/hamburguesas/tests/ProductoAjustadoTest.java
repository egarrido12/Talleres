package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;

public class ProductoAjustadoTest {

    private ProductoMenu productoBase;
    private ProductoAjustado productoAjustado;
    private Ingrediente ingredienteExtra1;
    private Ingrediente ingredienteExtra2;

    @BeforeEach
    public void setUp() {
        productoBase = new ProductoMenu("Hamburguesa", 15000);
        productoAjustado = new ProductoAjustado(productoBase);

        ingredienteExtra1 = new Ingrediente("Queso Extra", 2000);
        ingredienteExtra2 = new Ingrediente("Tocineta", 3000);
    }

    @Test
    public void testConstructor() {
        assertNotNull(productoAjustado, "El objeto ProductoAjustado debería haberse creado.");
        assertEquals("Hamburguesa", productoAjustado.getNombre(), "El nombre del producto no es el esperado.");
        assertEquals(15000, productoAjustado.getPrecio(), "El precio base no es el esperado.");
    }

    @Test
    public void testGetNombre() {
        assertEquals("Hamburguesa", productoAjustado.getNombre(), "El nombre devuelto no es el esperado.");
    }

    @Test
    public void testGetPrecioSinModificaciones() {
        assertEquals(15000, productoAjustado.getPrecio(), "El precio devuelto no es el esperado.");
    }

    @Test
    public void testGetPrecioConAgregados() {
        productoAjustado.agregarIngrediente(ingredienteExtra1);
        productoAjustado.agregarIngrediente(ingredienteExtra2);

        int precioEsperado = 15000 + 2000 + 3000;
        assertEquals(precioEsperado, productoAjustado.getPrecio(), "El precio con ingredientes adicionales no es el esperado.");
    }

    @Test
    public void testGenerarTextoFacturaSinModificaciones() {
        String expected = "Hamburguesa\n            15000\n";
        assertEquals(expected, productoAjustado.generarTextoFactura(), "El texto de la factura sin modificaciones no es el esperado.");
    }

    @Test
    public void testGenerarTextoFacturaConModificaciones() {
        productoAjustado.agregarIngrediente(ingredienteExtra1);
        productoAjustado.agregarIngrediente(ingredienteExtra2);
        productoAjustado.eliminarIngrediente(new Ingrediente("Lechuga", 0));

        String expected = "Hamburguesa\n" +
                          "    +Queso Extra                2000\n" +
                          "    +Tocineta                   3000\n" +
                          "    -Lechuga\n" +
                          "            20000\n";
        
        assertEquals(expected, productoAjustado.generarTextoFactura(), "El texto de la factura con modificaciones no es el esperado.");
    }
}