package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PedidoTest {

    private Pedido pedido;
    private ProductoMenu producto1;
    private ProductoMenu producto2;
    
    @BeforeEach
    public void setUp() {
        producto1 = new ProductoMenu("Hamburguesa", 15000);
        producto2 = new ProductoMenu("Papas", 5000);
        
        Pedido.resetNumeroPedidos(); 
        pedido = new Pedido("Juan Perez", "123 Calle Falsa");
    }

    @Test
    public void testConstructor() {
        assertNotNull(pedido, "El pedido debería haberse creado correctamente.");
        assertEquals("Juan Perez", pedido.getNombreCliente(), "El nombre del cliente no es el esperado.");
        assertEquals(0, pedido.getIdPedido(), "El ID del pedido no es el esperado en la primera creación.");
    }

    @Test
    public void testAgregarProducto() {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
        assertEquals(2, pedido.productos.size(), "La cantidad de productos no es la esperada después de agregar productos.");
    }

    @Test
    public void testGetPrecioTotalPedido() {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
        int precioEsperado = (int) ((15000 + 5000) * 1.19);
        assertEquals(precioEsperado, pedido.getPrecioTotalPedido(), "El precio total con IVA no es el esperado.");
    }

    @Test
    public void testGenerarTextoFactura() {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
        
        String facturaEsperada = "Cliente: Juan Perez\n" +
                                 "Dirección: 123 Calle Falsa\n" +
                                 "----------------\n" +
                                 producto1.generarTextoFactura() +
                                 producto2.generarTextoFactura() +
                                 "----------------\n" +
                                 "Precio Neto:  " + pedido.getPrecioNetoPedido() + "\n" +
                                 "IVA:          " + pedido.getPrecioIVAPedido() + "\n" +
                                 "Precio Total: " + pedido.getPrecioTotalPedido() + "\n";
        assertEquals(facturaEsperada, pedido.generarTextoFactura(), "El texto de la factura no es el esperado.");
    }

    @Test
    public void testGuardarFactura() throws IOException {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
        
        File archivo = new File("factura_test.txt");
        
        try {
            pedido.guardarFactura(archivo);
            String contenidoArchivo = new String(Files.readAllBytes(Paths.get(archivo.getPath())));
            assertEquals(pedido.generarTextoFactura(), contenidoArchivo, "El contenido de la factura guardada no es el esperado.");
        } catch (FileNotFoundException e) {
            fail("El archivo no debería lanzar FileNotFoundException.");
        } finally {
            archivo.delete(); // Limpieza del archivo después de la prueba
        }
    }

    @Test
    public void testGuardarFacturaException() {
        File archivoInvalido = new File("/ruta/invalida/factura_test.txt");
        
        assertThrows(FileNotFoundException.class, () -> {
            pedido.guardarFactura(archivoInvalido);
        }, "Se esperaba FileNotFoundException al intentar guardar en una ruta inválida.");
    }
}