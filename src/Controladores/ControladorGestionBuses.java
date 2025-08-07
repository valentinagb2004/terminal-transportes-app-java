/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Excepciones.BusExisteException;
import Excepciones.SinPlazasEstacionamientoException;
import Modelo.Bus;
import Modelo.Caseta;
import Util.Lista;

/**
 *
 * @author Valentina
 */
public class ControladorGestionBuses {
    
    private Caseta caseta;
    
    public ControladorGestionBuses(Caseta caseta) {
        this.caseta = caseta;
    }
    
    public void agregarBus(Bus bus) throws SinPlazasEstacionamientoException, BusExisteException {
        caseta.agregarBus(bus);
    }
    
    public Lista<Bus> getListaBuses() {
        return caseta.getListaBuses();
    }
    
    
}
