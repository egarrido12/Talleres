package uniandes.dpoo.aerolinea.modelo;

import java.util.Objects;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre una aerolínea.
 */
public class Ruta {

    private String codigoRuta;
    private Aeropuerto origen;
    private Aeropuerto destino;
    private String horaSalida;
    private String horaLlegada;

    /**
     * Constructor de la clase Ruta.
     *
     * @param origen       El aeropuerto de origen de la ruta.
     * @param destino      El aeropuerto de destino de la ruta.
     * @param horaSalida   La hora esperada de salida, expresada con una cadena de cuatro dígitos.
     * @param horaLlegada  La hora esperada de llegada, expresada con una cadena de cuatro dígitos.
     * @param codigoRuta   El código para la ruta.
     */
    public Ruta(Aeropuerto origen, Aeropuerto destino, String horaSalida, String horaLlegada, String codigoRuta) {
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.codigoRuta = codigoRuta;
    }

    /**
     * Retorna el código de la ruta.
     *
     * @return El código de la ruta.
     */
    public String getCodigoRuta() {
        return codigoRuta;
    }

    /**
     * Retorna el aeropuerto de origen de la ruta.
     *
     * @return El aeropuerto de origen.
     */
    public Aeropuerto getOrigen() {
        return origen;
    }

    /**
     * Retorna el aeropuerto de destino de la ruta.
     *
     * @return El aeropuerto de destino.
     */
    public Aeropuerto getDestino() {
        return destino;
    }

    /**
     * Retorna la hora esperada de salida.
     *
     * @return La hora esperada de salida.
     */
    public String getHoraSalida() {
        return horaSalida;
    }

    /**
     * Retorna la hora esperada de llegada.
     *
     * @return La hora esperada de llegada.
     */
    public String getHoraLlegada() {
        return horaLlegada;
    }

    /**
     * Calcula la duración esperada del vuelo en minutos.
     *
     * @return La duración en minutos.
     */
    public int getDuracion() {
        int horasSalida = getHoras(horaSalida);
        int minutosSalida = getMinutos(horaSalida);
        int horasLlegada = getHoras(horaLlegada);
        int minutosLlegada = getMinutos(horaLlegada);

        int duracionHoras = horasLlegada - horasSalida;
        int duracionMinutos = minutosLlegada - minutosSalida;

        if (duracionMinutos < 0) {
            duracionHoras -= 1;
            duracionMinutos += 60;
        }

        return duracionHoras * 60 + duracionMinutos;
    }

    /**
     * Dada una cadena con una hora y minutos, retorna los minutos.
     *
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres.
     * @return Una cantidad de minutos entre 0 y 59.
     */
    public static int getMinutos(String horaCompleta) {
        return Integer.parseInt(horaCompleta) % 100;
    }

    /**
     * Dada una cadena con una hora y minutos, retorna las horas.
     *
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres.
     * @return Una cantidad de horas entre 0 y 23.
     */
    public static int getHoras(String horaCompleta) {
        return Integer.parseInt(horaCompleta) / 100;
    }

}