/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Excepciones.BusInhabilitadoException;
import Modelo.Bus;
import Modelo.Caseta;
import Modelo.Viaje;
import Util.Lista;

/**
 *
 * @author Valentina
 */
public class ControladorGestionViajes {
    
    private Caseta caseta;
    
    public ControladorGestionViajes(Caseta caseta) {
        this.caseta = caseta;
    }
    
    public void agregarViaje(Viaje viaje) throws BusInhabilitadoException {
        caseta.agregarViaje(viaje);
    }
    
    public Lista<Bus> getListaBuses() {
        return caseta.getListaBuses();
    }
    
    public Lista<Viaje> getListaViajes(){
        return caseta.getListaViajes();
    }
    
}