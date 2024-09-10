package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Cliente;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

/**
 * Esta clase abstracta define el contrato para las posibles calculadoras de tarifa e implementa algunos métodos que pueden ser utilizados en varias implementaciones.
 */
public abstract class CalculadoraTarifas {

    public static final double IMPUESTO = 0.16; // Porcentaje de impuesto (16%)

    /**
     * Calcula la tarifa total para un vuelo, dado el vuelo y el cliente.
     * La tarifa total está constituida por un costo base, un descuento que podría aplicarse sobre el costo base,
     * y un impuesto que se aplica sobre el costo base menos el descuento.
     * 
     * @param vuelo El vuelo para el que se quiere calcular la tarifa
     * @param cliente El cliente para el que se quiere calcular la tarifa
     * @return El valor completo de la tarifa
     */
    public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
        int costoBase = calcularCostoBase(vuelo, cliente);
        double porcentajeDescuento = calcularPorcentajeDescuento(cliente);
        int valorDescuento = (int) (costoBase * porcentajeDescuento);
        int costoBaseMenosDescuento = costoBase - valorDescuento;
        int valorImpuestos = calcularValorImpuestos(costoBaseMenosDescuento);
        return costoBaseMenosDescuento + valorImpuestos;
    }

    /**
     * Calcula el valor de los impuestos para un tiquete, dado el costo base.
     * Los impuestos se calculan como un porcentaje sobre el costo base, usando la constante IMPUESTO.
     * 
     * @param costoBase El valor base del tiquete
     * @return El valor correspondiente a los impuestos
     */
    protected int calcularValorImpuestos(int costoBase) {
        return (int) (costoBase * IMPUESTO);
    }

    /**
     * Calcula la distancia aproximada en kilómetros para una ruta.
     * 
     * @param ruta La ruta para la cual se quiere calcular la distancia
     * @return Una aproximación de la distancia
     */
    protected int calcularDuracionVuelo(Ruta ruta) {
        // Implementación simple de ejemplo, se debería reemplazar por un cálculo real
        return ruta.getDuracion();
    }

    /**
     * Calcula el costo base de la tarifa dado el vuelo y el cliente.
     * Este método es abstracto y debe ser implementado por las clases que heredan de CalculadoraTarifas.
     * 
     * @param vuelo El vuelo para el que se quiere calcular el costo base
     * @param cliente El cliente para el que se quiere calcular el costo base
     * @return El valor base de la tarifa
     */
    protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);

    /**
     * Calcula el porcentaje de descuento que se le debería dar a un cliente dado su tipo y/o su historia.
     * Este método es abstracto y debe ser implementado por las clases que heredan de CalculadoraTarifas.
     * 
     * @param cliente El cliente para el que se quiere conocer el descuento
     * @return Un porcentaje de descuento, entre 0 y 1
     */
    protected abstract double calcularPorcentajeDescuento(Cliente cliente);
}