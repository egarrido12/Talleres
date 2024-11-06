package uniandes.dpoo.hamburguesas.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase utilitaria para manejar la creación y escritura de archivos
 */
public class UtilesArchivo {


	    public static void crearArchivo(File archivo, String contenido) throws IOException {
	        // Asegúrate de que el directorio exista
	        File directorio = archivo.getParentFile();
	        if (!directorio.exists()) {
	            if (!directorio.mkdirs()) {
	                throw new IOException("No se pudo crear el directorio: " + directorio.getPath());
	            }
	        }

	        // Crear el archivo si no existe
	        if (!archivo.exists()) {
	            if (!archivo.createNewFile()) {
	                throw new IOException("No se pudo crear el archivo: " + archivo.getPath());
	            }
	        }

	        // Escribir el contenido en el archivo
	        try (FileWriter fw = new FileWriter(archivo);
	             PrintWriter pw = new PrintWriter(fw)) {
	            pw.write(contenido);
	        }
	    }
	}
