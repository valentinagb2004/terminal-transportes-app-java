/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Excepciones.LimiteBusException;
import Excepciones.SinCuposDisponiblesException;
import Modelo.Caseta;
import Modelo.Cliente;
import Modelo.Notificacion;
import Modelo.Reserva;
import Modelo.Terminal;
import Modelo.Viaje;
import Util.Lista;

/**
 *
 * @author Valentina
 */
public class ControladorReservas {
   
    private Caseta caseta;
    private Terminal terminal;
    
    public ControladorReservas(Caseta caseta, Terminal terminal) {
        this.caseta = caseta;
        this.terminal = terminal;
    }
    
    public Lista<Viaje> listaTodosViajesDisponibles(String fechaSalida, String destino){
        return this.terminal.listaTodosViajesDisponibles(fechaSalida, destino);
    }
    
    public Lista<Reserva> listaTodasMisReservasActivas(String cedula) {
        return this.terminal.listaTodasMisReservasActivas(cedula);
    }
    
    public Caseta buscarCasetaPorBus(String placa){
        return terminal.buscarCasetaPorBus(placa);
    }
    
    public Notificacion cancelarReservas(Cliente cliente, Viaje viaje){
        return this.caseta.cancelarReservas(cliente, viaje);
    }
    
    public void crearReserva(Reserva reserva, Viaje viaje) throws LimiteBusException, SinCuposDisponiblesException{
        this.caseta.crearReservas(reserva, viaje);
    }
    
}
