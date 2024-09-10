package uniandes.dpoo.aerolinea.persistencia;

/**
 * Esta excepción se usa para indicar que se encontraron dos aeropuertos con el mismo código,
 * lo cual nunca debería ocurrir.
 */
public class AereopuertoDuplicadoException extends Exception {

    private String codigoDuplicado;

    /**
     * Crea una nueva excepción indicando que el código duplicado es el proporcionado.
     * 
     * @param codigo El código que está duplicado en dos aeropuertos.
     */
    public AereopuertoDuplicadoException(String codigo) {
        super("Ya existe un aeropuerto con el código: " + codigo);
        this.codigoDuplicado = codigo;
    }

    /**
     * Retorna el código que está duplicado en dos aeropuertos.
     * 
     * @return El código duplicado.
     */
    public String getCodigoDuplicado() {
        return codigoDuplicado;
    }
}
