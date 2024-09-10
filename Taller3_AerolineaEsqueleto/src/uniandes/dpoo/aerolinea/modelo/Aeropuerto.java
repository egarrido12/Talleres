package uniandes.dpoo.aerolinea.modelo;

import java.util.HashSet;
import java.util.Set;

import uniandes.dpoo.aerolinea.persistencia.AereopuertoDuplicadoException;

/**
 * Esta clase encapsula la información sobre los aeropuertos e implementa algunas operaciones
 * relacionadas con la ubicación geográfica de los aeropuertos. No puede haber dos aeropuertos
 * con el mismo código.
 */
public class Aeropuerto {

    private String codigo;
    private double latitud;
    private double longitud;
    private String nombre;
    private String nombreCiudad;
    private static Set<String> codigosUtilizados = new HashSet<>();
    private static final int RADIO_TERRESTRE = 6371; // Radio aproximado de la Tierra en kilómetros

    /**
     * Construye un nuevo aeropuerto e inicializa sus atributos con los valores dados.
     * El código del nuevo aeropuerto debe quedar almacenado en el conjunto codigosUtilizados
     * para mostrar que ya existe un aeropuerto con ese código.
     *
     * @param nombre       El nombre del aeropuerto.
     * @param codigo       El código del aeropuerto. Debe ser único.
     * @param nombreCiudad El nombre de la ciudad más próxima al aeropuerto.
     * @param latitud      La latitud del aeropuerto (un valor entre -90 y +90).
     * @param longitud     La longitud del aeropuerto (un valor entre -180 y +180).
     * @throws AereopuertoDuplicadoException Se lanza esta excepción si ya existe otro aeropuerto con el mismo código.
     */
    public Aeropuerto(String nombre, String codigo, String nombreCiudad, double latitud, double longitud) throws AereopuertoDuplicadoException {
        if (codigosUtilizados.contains(codigo)) {
            throw new AereopuertoDuplicadoException(codigo);
        }
        this.nombre = nombre;
        this.codigo = codigo;
        this.nombreCiudad = nombreCiudad;
        this.latitud = latitud;
        this.longitud = longitud;
        codigosUtilizados.add(codigo);
    }

    /**
     * Retorna el nombre del aeropuerto.
     *
     * @return El nombre del aeropuerto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el código del aeropuerto.
     *
     * @return El código del aeropuerto.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Retorna el nombre de la ciudad más próxima al aeropuerto.
     *
     * @return El nombre de la ciudad más próxima.
     */
    public String getNombreCiudad() {
        return nombreCiudad;
    }

    /**
     * Retorna la latitud del aeropuerto.
     *
     * @return La latitud del aeropuerto.
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * Retorna la longitud del aeropuerto.
     *
     * @return La longitud del aeropuerto.
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Calcula la distancia aproximada entre dos aeropuertos.
     * Utiliza la fórmula simplificada para obtener una estimación de la distancia.
     *
     * @param aeropuerto1 El primer aeropuerto.
     * @param aeropuerto2 El segundo aeropuerto.
     * @return La distancia en kilómetros entre los dos aeropuertos.
     */
    public static int calcularDistancia(Aeropuerto aeropuerto1, Aeropuerto aeropuerto2) {
        // Convertir los ángulos a radianes para facilitar las operaciones trigonométricas
        double latAeropuerto1 = Math.toRadians(aeropuerto1.getLatitud());
        double lonAeropuerto1 = Math.toRadians(aeropuerto1.getLongitud());
        double latAeropuerto2 = Math.toRadians(aeropuerto2.getLatitud());
        double lonAeropuerto2 = Math.toRadians(aeropuerto2.getLongitud());

        // Calcular la distancia debido a la diferencia de latitud y de longitud
        double deltaX = (lonAeropuerto2 - lonAeropuerto1) * Math.cos((latAeropuerto1 + latAeropuerto2) / 2);
        double deltaY = (latAeropuerto2 - latAeropuerto1);

        // Calcular la distancia real en kilómetros
        double distancia = Math.sqrt(deltaX * deltaX + deltaY * deltaY) * RADIO_TERRESTRE;

        return (int) Math.round(distancia);
    }
}