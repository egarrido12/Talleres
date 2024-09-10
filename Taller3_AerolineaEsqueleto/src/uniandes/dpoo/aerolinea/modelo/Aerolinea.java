package uniandes.dpoo.aerolinea.modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.persistencia.IPersistenciaAerolinea;
import uniandes.dpoo.aerolinea.persistencia.IPersistenciaTiquetes;
import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;
import uniandes.dpoo.aerolinea.modelo.Tiquete;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifasTemporadaAlta;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifasTemporadaBaja;
/**
 * En esta clase se organizan todos los aspectos relacionados con una Aerolínea.
 * 
 * Por un lado, esta clase cumple un rol central como estructurador para todo el resto de elementos: directa o indirectamente, todos están contenidos y se pueden acceder a
 * través de la clase Aerolínea.
 * 
 * Por otro lado, esta clase implementa algunas funcionalidades adicionales a su rol como estructurador, para lo cual se apoya en las otras clases que hacen parte del
 * proyecto.
 */
public class Aerolinea {
    
    private List<Avion> aviones;
    private Map<String, Ruta> rutas;
    private List<Vuelo> vuelos;
    private Map<String, Cliente> clientes;

    public Aerolinea() {
        aviones = new LinkedList<>();
        rutas = new HashMap<>();
        vuelos = new LinkedList<>();
        clientes = new HashMap<>();
    }

    public void agregarRuta(Ruta ruta) {
        this.rutas.put(ruta.getCodigoRuta(), ruta);
    }

    public void agregarAvion(Avion avion) {
        this.aviones.add(avion);
    }

    public void agregarCliente(Cliente cliente) {
        this.clientes.put(cliente.getIdentificador(), cliente);
    }

    public boolean existeCliente(String identificadorCliente) {
        return this.clientes.containsKey(identificadorCliente);
    }

    public Cliente getCliente(String identificadorCliente) {
        return this.clientes.get(identificadorCliente);
    }

    public Collection<Avion> getAviones() {
        return aviones;
    }

    public Collection<Ruta> getRutas() {
        return rutas.values();
    }
    
    public Collection<Cliente> getClientes() {
        return clientes.values();
    }

    public Ruta getRuta(String codigoRuta) {
        return rutas.get(codigoRuta);
    }

    public Collection<Vuelo> getVuelos() {
        return vuelos;
    }

    public Vuelo getVuelo(String codigoRuta, String fechaVuelo) {
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getRuta().getCodigoRuta().equals(codigoRuta) && vuelo.getFecha().equals(fechaVuelo)) {
                return vuelo;
            }
        }
        return null;
    }

    public Collection<Tiquete> getTiquetes() {
        List<Tiquete> todosTiquetes = new ArrayList<>();
        for (Vuelo vuelo : vuelos) {
            todosTiquetes.addAll(vuelo.getTiquetes());
        }
        return todosTiquetes;
    }

    public void cargarAerolinea(String archivo, String tipoArchivo) throws TipoInvalidoException, IOException, InformacionInconsistenteException {
        IPersistenciaAerolinea persistencia = CentralPersistencia.getPersistenciaAerolinea(tipoArchivo);
        persistencia.cargarAerolinea(archivo, this);
    }

    public void salvarAerolinea(String archivo, String tipoArchivo) throws TipoInvalidoException, IOException {
        IPersistenciaAerolinea persistencia = CentralPersistencia.getPersistenciaAerolinea(tipoArchivo);
        persistencia.salvarAerolinea(archivo, this);
    }
    
    public void cargarTiquetes(String archivo, String tipoArchivo)
            throws TipoInvalidoException, IOException, InformacionInconsistenteException {
        IPersistenciaTiquetes persistencia = CentralPersistencia.getPersistenciaTiquetes(tipoArchivo);
        persistencia.cargarTiquetes(archivo,this);
    }

    public void salvarTiquetes(String archivo, String tipoArchivo) throws TipoInvalidoException, IOException {
        IPersistenciaTiquetes persistencia = CentralPersistencia.getPersistenciaTiquetes(tipoArchivo);
        persistencia.salvarTiquetes(archivo, this);
    }
    
    public void programarVuelo(String fecha, String codigoRuta, String nombreAvion) throws Exception {
        Ruta ruta = getRuta(codigoRuta);
        if (ruta == null) {
            throw new Exception("Ruta no encontrada.");
        }

        Avion avion = null;
        for (Avion a : aviones) {
            if (a.getNombre().equals(nombreAvion)) {
                avion = a;
                break;
            }
        }

        if (avion == null) {
            throw new Exception("Avión no encontrado.");
        }

        for (Vuelo vuelo : vuelos) {
            if (vuelo.getAvion().equals(avion) && vuelo.getFecha().equals(fecha)) {
                throw new Exception("El avión ya está ocupado en esta fecha.");
            }
        }

        Vuelo nuevoVuelo = new Vuelo(ruta, fecha, avion);
        vuelos.add(nuevoVuelo);
    }

    public int venderTiquetes(String identificadorCliente, String fecha, String codigoRuta, int cantidad) throws VueloSobrevendidoException, Exception {
        Cliente cliente = getCliente(identificadorCliente);
        if (cliente == null) {
            throw new Exception("Cliente no encontrado.");
        }

        Vuelo vuelo = getVuelo(codigoRuta, fecha);
        if (vuelo == null) {
            throw new Exception("Vuelo no encontrado.");
        }

        if (cantidad > vuelo.getAvion().getCapacidad() - vuelo.getTiquetes().size()) {
            throw new VueloSobrevendidoException(vuelo);
        }

        boolean esTemporadaAlta = esTemporadaAlta(fecha);
        CalculadoraTarifas calculadora = esTemporadaAlta ? new CalculadoraTarifasTemporadaAlta() : new CalculadoraTarifasTemporadaBaja();

        int valorTotal = vuelo.venderTiquetes(cliente, calculadora, cantidad);
        return valorTotal;
    }

    private boolean esTemporadaAlta(String fecha) {
        // Implementa la lógica para determinar si es temporada alta o baja
        // Ejemplo: si la fecha está en diciembre, enero, febrero, julio o agosto, es temporada alta
        // Necesitas usar una biblioteca de fechas para verificar el mes.
        return false; // Cambia esto con la lógica real
    }

    public void registrarVueloRealizado(String fecha, String codigoRuta) {
        // Implementa la lógica para registrar el vuelo realizado si es necesario
    }

    public String consultarSaldoPendienteCliente(String identificadorCliente) {
        Cliente cliente = getCliente(identificadorCliente);
        if (cliente == null) {
            return "Cliente no encontrado.";
        }

        int saldoPendiente = 0;
        for (Tiquete tiquete : getTiquetes()) {
            if (tiquete.getCliente().equals(cliente) && !tiquete.esUsado()) {
                saldoPendiente += tiquete.getTarifa();
            }
        }

        return "Saldo pendiente: " + saldoPendiente;
    }
}