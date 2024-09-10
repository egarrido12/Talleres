package uniandes.dpoo.aerolinea.modelo;
import uniandes.dpoo.aerolinea.modelo.Cliente;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

/**
 * Esta clase agrupa la información de un tiquete, expedido para un vuelo específico
 * en una cierta fecha, y que fue comprado por un cliente. Cuando se crea, un tiquete
 * no está usado. Después de que se haya realizado el vuelo, el tiquete debe quedar 
 * marcado como usado.
 */
public class Tiquete {

    // Campos privados de la clase
    private Cliente cliente;      // El cliente que compró el tiquete
    private String codigo;        // El código único del tiquete
    private int tarifa;           // El valor que debió pagar el cliente por este tiquete
    private boolean usado;        // Indica si el tiquete ya fue usado o no
    private Vuelo vuelo;          // El vuelo en el que se usará el tiquete

    /**
     * Constructor de la clase Tiquete.
     * 
     * @param codigo El código del tiquete, debe ser único
     * @param vuelo El vuelo para el cual se compró el tiquete
     * @param clienteComprador El cliente que compró el tiquete
     * @param tarifa La tarifa pagada por el tiquete
     */
    public Tiquete(String codigo, Vuelo vuelo, Cliente clienteComprador, int tarifa) {
        this.codigo = codigo;
        this.vuelo = vuelo;
        this.cliente = clienteComprador;
        this.tarifa = tarifa;
        this.usado = false;  // Al crear el tiquete, inicialmente no está usado
    }

    /**
     * Retorna el cliente que compró el tiquete.
     * 
     * @return El cliente que compró el tiquete.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Retorna el vuelo asociado al tiquete.
     * 
     * @return El vuelo en el que se usará el tiquete.
     */
    public Vuelo getVuelo() {
        return vuelo;
    }

    /**
     * Retorna el código único del tiquete.
     * 
     * @return El código del tiquete.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Retorna la tarifa pagada por el tiquete.
     * 
     * @return La tarifa pagada por el tiquete.
     */
    public int getTarifa() {
        return tarifa;
    }

    /**
     * Cambia el estado del tiquete para marcarlo como usado.
     */
    public void marcarComoUsado() {
        this.usado = true;
    }

    /**
     * Indica si el tiquete ya fue usado.
     * 
     * @return true si el tiquete ya fue usado, false de lo contrario.
     */
    public boolean esUsado() {
        return usado;
    }

}