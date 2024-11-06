package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {

    private ProductoMenu producto;

    @BeforeEach
    public void setUp() {
        // Crear una instancia de ProductoMenu antes de cada prueba
        producto = new ProductoMenu("Hamburguesa", 15000);
    }

    @Test
    public void testConstructor() {
        // Verificar que el objeto se creó correctamente y que los atributos se asignaron como se esperaba
        assertNotNull(producto, "El objeto ProductoMenu debería haberse creado.");
        assertEquals("Hamburguesa", producto.getNombre(), "El nombre del producto no es el esperado.");
        assertEquals(15000, producto.getPrecio(), "El precio base no es el esperado.");
    }

    @Test
    public void testGetNombre() {
        // Verificar que el método getNombre devuelve el nombre asignado
        assertEquals("Hamburguesa", producto.getNombre(), "El nombre devuelto no es el esperado.");
    }
    
    @Test
    public void testGetPrecio() {
        // Verificar que el método getPrecio devuelve el precio asignado
        assertEquals(15000, producto.getPrecio(), "El precio devuelto no es el esperado.");
    }

    @Test
    public void testGenerarTextoFactura() {
        // Verificar que el método generarTextoFactura devuelve el formato correcto
        String expected = "Hamburguesa\n            15000\n";
        assertEquals(expected, producto.generarTextoFactura(), "El texto de la factura no es el esperado.");
    }
}