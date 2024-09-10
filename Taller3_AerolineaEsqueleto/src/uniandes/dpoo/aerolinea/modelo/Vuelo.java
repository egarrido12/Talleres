package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.aerolinea.persistencia.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;

/**
 * Esta clase tiene la información de un vuelo particular que cubre una ruta y se lleva a cabo en una cierta fecha.
 * Los vuelos son las unidades a las cuales están asociadas las ventas de tiquetes.
 */
public class Vuelo {

    private Avion avion;                  // El avión utilizado para realizar el vuelo
    private String fecha;                // La fecha para el vuelo, expresada como una cadena de la forma YYYY-MM-DD
    private Ruta ruta;                   // La ruta del vuelo
    private Map<String, Tiquete> tiquetes; // Los tiquetes vendidos para el vuelo

    /**
     * Constructor de la clase Vuelo.
     * 
     * @param ruta La ruta que cubrirá el vuelo
     * @param fecha La fecha para el vuelo, expresada como una cadena de la forma YYYY-MM-DD
     * @param avion El avión que realizará el vuelo
     */
    public Vuelo(Ruta ruta, String fecha, Avion avion) {
        this.ruta = ruta;
        this.fecha = fecha;
        this.avion = avion;
        this.tiquetes = new HashMap<>(); // Inicializa el mapa de tiquetes
    }

    /**
     * Retorna la ruta del vuelo.
     * 
     * @return La ruta del vuelo.
     */
    public Ruta getRuta() {
        return ruta;
    }

    /**
     * Retorna la fecha del vuelo.
     * 
     * @return La fecha del vuelo.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Retorna el avión utilizado para el vuelo.
     * 
     * @return El avión utilizado para el vuelo.
     */
    public Avion getAvion() {
        return avion;
    }

    /**
     * Retorna una colección de tiquetes vendidos para el vuelo.
     * 
     * @return La colección de tiquetes vendidos.
     */
    public Collection<Tiquete> getTiquetes() {
        return tiquetes.values();
    }

    /**
     * Vende una determinada cantidad de tiquetes para el vuelo y los deja registrados en el mapa de tiquetes.
     * 
     * @param cliente El cliente al cual se le venden los tiquetes
     * @param calculadora La calculadora de tarifas que debe usarse para saber el precio por tiquete
     * @param cantidad La cantidad de tiquetes que se quieren comprar
     * @return El valor total de los tiquetes vendidos
     * @throws VueloSobrevendidoException Si no hay suficiente espacio en el vuelo para todos los pasajeros
     */
    public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad)  {
        // Verificar si hay suficiente espacio disponible en el vuelo
        int tiquetesVendidos = tiquetes.size();
        int capacidadDisponible = avion.getCapacidad() - tiquetesVendidos;

        int valorTotal = 0;
        for (int i = 0; i < cantidad; i++) {
            String codigo = generarCodigoTiquete(); // Método para generar un código único para el tiquete
            int tarifa = calculadora.calcularTarifa(this, cliente); // Calcula la tarifa del tiquete
            Tiquete tiquete = new Tiquete(codigo, this, cliente, tarifa);
            tiquetes.put(codigo, tiquete);
            valorTotal += tarifa;
        }

        return valorTotal;
    }

    /**
     * Genera un código único para un tiquete.
     * 
     * @return Un código único para el tiquete.
     */
    private String generarCodigoTiquete() {
        return "TQ" + System.currentTimeMillis();
    }
}