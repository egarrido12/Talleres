package uniandes.dpoo.aerolinea.modelo;

/**
 * Esta clase organiza la información básica de los aviones que realizan los vuelos.
 */
public class Avion {

    private int capacidad;    // La capacidad del avión
    private String nombre;   // El nombre con el que se identifica al avión

    /**
     * Constructor de la clase Avion.
     * 
     * @param nombre El nombre del avión
     * @param capacidad La capacidad del avión
     */
    public Avion(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    /**
     * Retorna la capacidad del avión.
     * 
     * @return La capacidad del avión.
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Retorna el nombre del avión.
     * 
     * @return El nombre del avión.
     */
    public String getNombre() {
        return nombre;
    }
   }