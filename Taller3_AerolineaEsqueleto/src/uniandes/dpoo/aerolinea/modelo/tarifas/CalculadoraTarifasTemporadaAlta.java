package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Cliente;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
/**
 * Esta clase se utiliza para calcular las tarifas en temporada alta,
 * cuando los vuelos no tienen descuento y se aplica la misma tarifa por kilómetro a todos los clientes.
 */
public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {

    // El costo por kilómetro para temporada alta
    protected static final int COSTO_POR_KM = 10; 

    /**
     * Constructor de la calculadora de tarifas para temporada alta.
     */
    public CalculadoraTarifasTemporadaAlta() {
        super();
    }

    /**
     * Calcula el costo base como COSTO_POR_KM x distancia.
     * 
     * @param vuelo El vuelo para el que se quiere calcular la tarifa
     * @param cliente El cliente para el que se quiere calcular la tarifa
     * @return El valor base de la tarifa
     */
    
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        int distancia = calcularDistanciaVuelo(vuelo.getRuta());
        return COSTO_POR_KM * distancia;
    }
    private int calcularDistanciaVuelo(Ruta ruta) {
        Aeropuerto origen = ruta.getOrigen();
        Aeropuerto destino = ruta.getDestino();
        return Aeropuerto.calcularDistancia(origen, destino);
    }
    /**
     * Calcula el porcentaje de descuento que se le debería dar a un cliente dado su tipo y/o su historia.
     * En temporada alta, no se aplica descuento.
     * 
     * @param cliente El cliente para el que se quiere conocer el descuento
     * @return Un porcentaje de descuento, que siempre es 0 en temporada alta
     */
    
   
    protected double calcularPorcentajeDescuento(Cliente cliente) {
        return 0.0; // En temporada alta no hay descuento
    }
}