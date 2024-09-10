package uniandes.dpoo.aerolinea.persistencia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {

    public PersistenciaAerolineaJson() {
    }

    
    public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws IOException, TipoInvalidoException {
        try (FileInputStream inputStream = new FileInputStream(archivo)) {
            // Leer el archivo completo en un String
            byte[] data = inputStream.readAllBytes();
            String jsonString = new String(data, StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(jsonString);
            // Cargar aviones
            JSONArray avionesArray = jsonObject.getJSONArray("aviones");
            for (int i = 0; i < avionesArray.length(); i++) {
                JSONObject avionJson = avionesArray.getJSONObject(i);
                String matricula = avionJson.getString("matricula");
                int capacidad = avionJson.getInt("capacidad");
                Avion avion = new Avion(matricula, capacidad);
                aerolinea.agregarAvion(avion);
            }

            // Cargar rutas
            JSONArray rutasArray = jsonObject.getJSONArray("rutas");
            for (int i = 0; i < rutasArray.length(); i++) {
                JSONObject rutaJson = rutasArray.getJSONObject(i);
                String origen = rutaJson.getString("origen");
                String destino = rutaJson.getString("destino");
                Ruta ruta = new Ruta(null, null, origen, destino, destino);
                aerolinea.agregarRuta(ruta);
            }

            // Cargar aeropuertos
            // Si la clase Aerolinea no tiene un método para agregar aeropuertos, este paso puede omitirse
            // Alternativamente, se puede añadir un método para agregar aeropuertos en Aerolinea
        } catch (Exception e) {
            throw new TipoInvalidoException("Error al cargar la información de la aerolínea desde el archivo.");
        }
    }

    
    public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException {
        JSONObject jsonObject = new JSONObject();

        // Guardar aviones
        JSONArray avionesArray = new JSONArray();
        for (Avion avion : aerolinea.getAviones()) {
            JSONObject avionJson = new JSONObject();
            avionJson.put("matricula", avion.getNombre());
            avionJson.put("capacidad", avion.getCapacidad());
            avionesArray.put(avionJson);
        }
        jsonObject.put("aviones", avionesArray);

        // Guardar rutas
        JSONArray rutasArray = new JSONArray();
        for (Ruta ruta : aerolinea.getRutas()) {
            JSONObject rutaJson = new JSONObject();
            rutaJson.put("origen", ruta.getOrigen());
            rutaJson.put("destino", ruta.getDestino());
            rutasArray.put(rutaJson);
        }
        jsonObject.put("rutas", rutasArray);

        // Guardar aeropuertos
        // Si la clase Aerolinea tiene un método para obtener aeropuertos, se puede añadir aquí
        // Por ahora, este paso se omite

        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(jsonObject.toString(4)); // Formatea el JSON con una indentación de 4 espacios
        }
    }
}