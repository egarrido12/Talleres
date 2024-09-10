package uniandes.dpoo.aerolinea.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    private List<Tiquete> tiquetesSinUsar;
    private List<Tiquete> tiquetesUsados;

    // Constructor que inicializa las listas de tiquetes sin usar y usados
    public Cliente() {
        this.tiquetesSinUsar = new ArrayList<>();
        this.tiquetesUsados = new ArrayList<>();
    }

    // Método abstracto que debe ser implementado por las subclases para retornar el identificador del cliente
    public abstract String getIdentificador();

    
    public abstract String getTipoCliente();

    
    public void agregarTiquete(Tiquete tiquete) {
        tiquetesSinUsar.add(tiquete);
    }

    // Calcula el valor total de los tiquetes que ha comprado el cliente (sin usar)
    public int calcularValorTotalTiquetes() {
        int valorTotal = 0;
        for (Tiquete tiquete : tiquetesSinUsar) {
            valorTotal += tiquete.getTarifa();
        }
        return valorTotal;
    }

    public void usarTiquetes(Vuelo vuelo) {
        List<Tiquete> tiquetesParaUsar = new ArrayList<>();
        for (Tiquete tiquete : tiquetesSinUsar) {
            if (tiquete.getVuelo().equals(vuelo)) {
                tiquetesParaUsar.add(tiquete);
            }
        }

       
        tiquetesSinUsar.removeAll(tiquetesParaUsar);
        tiquetesUsados.addAll(tiquetesParaUsar);
    }

  
    public List<Tiquete> getTiquetesSinUsar() {
        return tiquetesSinUsar;
    }

    public List<Tiquete> getTiquetesUsados() {
        return tiquetesUsados;
    }

 }


