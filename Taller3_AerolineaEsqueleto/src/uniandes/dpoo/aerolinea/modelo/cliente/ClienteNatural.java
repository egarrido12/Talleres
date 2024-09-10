package uniandes.dpoo.aerolinea.modelo.cliente;

import uniandes.dpoo.aerolinea.modelo.Cliente;
/**
 * Esta clase representa a los clientes de la aerolínea que son personas naturales.
 */
public class ClienteNatural extends Cliente {
    
    public static final String PERSONAL = "Natural";  // Constante para identificar el tipo de cliente

	public static final String NATURAL = null;

    private String nombre;  // Nombre del cliente natural

    // Constructor
    public ClienteNatural(String nombre) {
        super();  // Llama al constructor de la clase Cliente
        this.nombre = nombre;
    }

    /**
     * Retorna el identificador del cliente.
     * En este caso, el identificador es el nombre del cliente.
     *
     * @return Identificador del cliente (nombre).
     */
    
    public String getIdentificador() {
        return this.nombre;
    }

    /**
     * Retorna el tipo de cliente.
     * En este caso, el tipo es "Natural".
     *
     * @return Una cadena que identifica al tipo de cliente ("Natural").
     */

    public String getTipoCliente() {
        return PERSONAL;
    }
  }
