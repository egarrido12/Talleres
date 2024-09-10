package uniandes.dpoo.aerolinea.modelo.cliente;

import org.json.JSONObject;
import uniandes.dpoo.aerolinea.modelo.Cliente;

/**
 * Esta clase se usa para representar a los clientes de la aerolínea que son empresas.
 */
public class ClienteCorporativo extends Cliente {
    
    // Constantes para el tipo de cliente y tamaños de empresa
    public static final String CORPORATIVO = "Corporativo";
    public static final int PEQUENA = 1;
    public static final int MEDIANA = 2;
    public static final int GRANDE = 3;

    // Atributos
    private String nombreEmpresa;
    private int tamanoEmpresa; // Tamaño de la empresa: PEQUENA, MEDIANA o GRANDE

    /**
     * Constructor de la clase ClienteCorporativo.
     * 
     * @param nombreEmpresa Nombre de la empresa.
     * @param tamano Tamaño de la empresa: PEQUENA, MEDIANA o GRANDE.
     */
    public ClienteCorporativo(String nombreEmpresa, int tamano) {
        super();  // Llama al constructor de la clase Cliente
        this.nombreEmpresa = nombreEmpresa;
        this.tamanoEmpresa = tamano;
    }

    /**
     * Retorna el identificador único del cliente.
     * 
     * @return Identificador del cliente.
     */
    @Override
    public String getIdentificador() {
        return "Empresa: " + nombreEmpresa;
    }

    /**
     * Retorna el tipo de cliente.
     * 
     * @return Tipo de cliente.
     */
    @Override
    public String getTipoCliente() {
        return CORPORATIVO;
    }

    /**
     * Retorna el nombre de la empresa.
     * 
     * @return Nombre de la empresa.
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * Retorna el tamaño de la empresa.
     * 
     * @return Tamaño de la empresa: PEQUENA, MEDIANA o GRANDE.
     */
    public int getTamanoEmpresa() {
        return tamanoEmpresa;
    }

    /**
     * Carga un cliente corporativo desde un objeto JSON.
     * 
     * @param cliente Objeto JSON que contiene la información del cliente.
     * @return Nuevo objeto ClienteCorporativo inicializado con la información del JSON.
     */
    public static ClienteCorporativo cargarDesdeJSON(JSONObject cliente) {
        String nombreEmpresa = cliente.getString("nombreEmpresa");
        int tamanoEmpresa = cliente.getInt("tamanoEmpresa");
        return new ClienteCorporativo(nombreEmpresa, tamanoEmpresa);
    }

    /**
     * Salva la información del cliente corporativo en un objeto JSON.
     * 
     * @return Objeto JSON con toda la información del cliente corporativo.
     */
    public JSONObject salvarEnJSON() {
        JSONObject jobject = new JSONObject();
        jobject.put("nombreEmpresa", this.nombreEmpresa);
        jobject.put("tamanoEmpresa", this.tamanoEmpresa);
        jobject.put("tipo", CORPORATIVO);
        return jobject;
    }
}

