/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Excepciones.LimiteBusException;
import Excepciones.SinCuposDisponiblesException;
import Excepciones.ReservaExisteException;
import Excepciones.SinPuntosAcumuladosException;
import Excepciones.TiqueteExisteException;
import Excepciones.ValorTiqueteMaximoPuntosException;
import Modelo.Caseta;
import Modelo.Cliente;
import Modelo.Notificacion;
import Modelo.Tiquete;
import Modelo.Viaje;
import Util.Lista;

/**
 *
 * @author Valentina
 */
public class ControladorVentaTiquetes {
    
    private Caseta caseta;
    
    public ControladorVentaTiquetes(Caseta caseta) {
        this.caseta = caseta;
    }
    
    public Lista<Viaje> getListaViajes() {
        return caseta.getListaViajes();
    }
    
    public void agregarVentaTiquetes(Cliente cliente, Tiquete tiquete, Viaje viaje, int cantidadTiquetes) throws SinCuposDisponiblesException, LimiteBusException{
         caseta.agregarVentaTiquetes(cliente, tiquete, viaje, cantidadTiquetes);
    }
    
    public void agregarVentaTiquetesConPuntos(Cliente cliente, Tiquete tiquete, Viaje viaje, int cantidadTiquetes) throws SinCuposDisponiblesException, LimiteBusException, 
            SinPuntosAcumuladosException, ValorTiqueteMaximoPuntosException{
        
         caseta.agregarVentaTiquetesConPuntos(cliente, tiquete, viaje, cantidadTiquetes);
    }
    
    public void confirmarReserva(Cliente cliente, Viaje viaje) throws ReservaExisteException{
         caseta.confirmarReserva(cliente, viaje);
    }
    
    public Notificacion devolucion(Cliente cliente, Viaje viaje) throws TiqueteExisteException{
         return caseta.devolucion(cliente, viaje);
    }
    
}
