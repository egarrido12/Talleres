package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import java.util.ArrayList;

public class ComboTest {

    private Combo combo;
    private ProductoMenu producto1;
    private ProductoMenu producto2;
    private ArrayList<ProductoMenu> productos;

    @BeforeEach
    public void setUp() {
        // Configura productos base para el combo
        producto1 = new ProductoMenu("Hamburguesa", 15000);
        producto2 = new ProductoMenu("Papas", 5000);
        
        productos = new ArrayList<>();
        productos.add(producto1);
        productos.add(producto2);
        
        // Configura el combo con descuento del 20% (0.8)
        combo = new Combo("Combo Familiar", 0.8, productos);
    }

    @Test
    public void testConstructor() {
        assertNotNull(combo, "El combo debería haberse creado correctamente.");
        assertEquals("Combo Familiar", combo.getNombre(), "El nombre del combo no es el esperado.");
        assertEquals(0.8, combo.descuento, "El descuento no es el esperado.");
    }

    @Test
    public void testGetNombre() {
        assertEquals("Combo Familiar", combo.getNombre(), "El nombre devuelto no es el esperado.");
    }

    @Test
    public void testGetPrecioConDescuento() {
        int precioEsperado = (int) ((15000 + 5000) * 0.8); // Descuento del 20%
        assertEquals(precioEsperado, combo.getPrecio(), "El precio con descuento no es el esperado.");
    }

    @Test
    public void testGenerarTextoFactura() {
        String expected = "Combo Combo Familiar\n" +
                          " Descuento: 0.8\n" +
                          "            " + combo.getPrecio() + "\n";
        assertEquals(expected, combo.generarTextoFactura(), "El texto de la factura no es el esperado.");
    }
}