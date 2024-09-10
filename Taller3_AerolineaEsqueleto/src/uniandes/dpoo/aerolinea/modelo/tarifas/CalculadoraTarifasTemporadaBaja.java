package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Cliente;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

/**
 * Esta clase se utiliza para calcular las tarifas en temporada baja.
 * En temporada baja, los clientes que son personas naturales tienen una tarifa base diferente a la de los clientes corporativos.
 * Adicionalmente, los clientes corporativos tienen un descuento diferente según el tamaño.
 */
public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {

    // El costo por kilómetro en temporada baja para clientes corporativos
    protected static final int COSTO_POR_KM_CORPORATIVO = 5; // Ejemplo: 5 unidades monetarias por kilómetro

    // El costo por kilómetro en temporada baja para personas naturales
    protected static final int COSTO_POR_KM_NATURAL = 8; // Ejemplo: 8 unidades monetarias por kilómetro

    // El descuento que se le puede aplicar a empresas pequeñas
    protected static final double DESCUENTO_PEQ = 0.1; // 10% de descuento

    // El descuento que se le puede aplicar a empresas medianas
    protected static final double DESCUENTO_MEDIANAS = 0.2; // 20% de descuento

    // El descuento que se le puede aplicar a empresas grandes
    protected static final double DESCUENTO_GRANDES = 0.3; // 30% de descuento

    /**
     * Constructor de la calculadora de tarifas para temporada baja.
     */
    public CalculadoraTarifasTemporadaBaja() {
        super();
    }

    /**
     * Calcula el costo base como COSTO_POR_KM x distancia.
     * 
     * @param vuelo El vuelo para el que se quiere calcular la tarifa
     * @param cliente El cliente para el que se quiere calcular la tarifa
     * @return El valor base de la tarifa
     */
    @Override
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        int distancia = calcularDistanciaVuelo(vuelo.getRuta());
        int costoPorKm = (cliente instanceof ClienteCorporativo) ? COSTO_POR_KM_CORPORATIVO : COSTO_POR_KM_NATURAL;
        return costoPorKm * distancia;
    }
    
    private int calcularDistanciaVuelo(Ruta ruta) {
        Aeropuerto origen = ruta.getOrigen();
        Aeropuerto destino = ruta.getDestino();
        return Aeropuerto.calcularDistancia(origen, destino);
    }

    /**
     * Calcula el porcentaje de descuento que se le debería dar a un cliente dado su tipo y/o su historia.
     * 
     * @param cliente El cliente para el que se quiere conocer el descuento
     * @return Un porcentaje de descuento, entre 0 y 1
     */
    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente) {
        if (cliente instanceof ClienteCorporativo) {
            ClienteCorporativo corporativo = (ClienteCorporativo) cliente;
            int tamano = corporativo.getTamanoEmpresa();
            if (tamano >= 1000) {
                return DESCUENTO_GRANDES;
            } else if (tamano >= 500) {
                return DESCUENTO_MEDIANAS;
            } else {
                return DESCUENTO_PEQ;
            }
        }
        return 0.0; // No hay descuento para clientes personales en temporada baja
    }
}