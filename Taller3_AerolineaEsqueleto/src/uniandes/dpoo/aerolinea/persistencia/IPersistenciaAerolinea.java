package uniandes.dpoo.aerolinea.persistencia;

import java.io.IOException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;

/**
 * Interfaz para manejar la persistencia de datos de una aerolínea.
 * Permite cargar y salvar la información de la aerolínea en un archivo.
 */
public interface IPersistenciaAerolinea {

    /**
     * Carga la información de todos los elementos de una aerolínea, excepto los clientes y tiquetes,
     * y actualiza la estructura de objetos que se encuentra dentro de la aerolínea.
     *
     * @param archivo La ruta al archivo que contiene la información que se va a cargar.
     * @param aerolinea La aerolínea dentro de la cual debe almacenarse la información.
     * @throws IOException Si hay problemas leyendo el archivo.
     * @throws TipoInvalidoException Si hay información inconsistente dentro del archivo, 
     *                                             o entre el archivo y el estado de la aerolínea.
     */
    void cargarAerolinea(String archivo, Aerolinea aerolinea) throws IOException, TipoInvalidoException;

    /**
     * Salva en un archivo la información de todos los elementos de una aerolínea, excepto los clientes y tiquetes.
     *
     * @param archivo La ruta al archivo donde debe quedar almacenada la información.
     * @param aerolinea La aerolínea que tiene la información que se quiere almacenar.
     * @throws IOException Si hay problemas escribiendo el archivo.
     */
    void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException;
}