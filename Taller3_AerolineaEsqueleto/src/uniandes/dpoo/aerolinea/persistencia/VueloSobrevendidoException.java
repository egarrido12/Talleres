package uniandes.dpoo.aerolinea.persistencia;

import uniandes.dpoo.aerolinea.modelo.Vuelo;

/**
 * Esta clase se usa para anunciar que se intentó vender un tiquete para un vuelo que ya está lleno.
 */
public class VueloSobrevendidoException extends Exception {

    private Vuelo vuelo;

    /**
     * Constructor de la excepción.
     * 
     * @param vuelo El vuelo que está sobrevendido.
     */
    public VueloSobrevendidoException(Vuelo vuelo) {
        super("El vuelo " + vuelo + " está sobrevendido.");
        this.vuelo = vuelo;
    }

    /**
     * Retorna el vuelo que está sobrevendido.
     * 
     * @return El vuelo que está sobrevendido.
     */
    public Vuelo getVuelo() {
        return vuelo;
    }
}