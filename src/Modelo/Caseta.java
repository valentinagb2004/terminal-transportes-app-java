/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Excepciones.BusExisteException;
import Excepciones.BusInhabilitadoException;
import Excepciones.LimiteBusException;
import Excepciones.SinCuposDisponiblesException;
import Excepciones.ReservaExisteException;
import Excepciones.SinPlazasEstacionamientoException;
import Excepciones.SinPuntosAcumuladosException;
import Excepciones.TiqueteExisteException;
import Excepciones.ValorTiqueteMaximoPuntosException;
import Util.Lista;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Valentina
 */
public class Caseta implements Serializable{
    private String nit;
    private String nombreEmpresa;
    private double valorCanonArrendamiento;
    private int cantidadPlazasEstacionamiento;
    private AdminFlota adminFlota;

   private Lista<Bus> listaBuses;
   private Lista<Viaje> listaViajes;
   private Lista<Devolucion> listaDevoluciones;

    public Caseta() {
        listaBuses = new Lista<>();
        listaViajes = new Lista<>();
        listaDevoluciones = new Lista<>();
    }
    
    public boolean esBlanco() {
        if (adminFlota == null) {
            return true;
        }
        return false;
    }

    public boolean esAzul() {
        if (adminFlota != null) {
            return true;
        }
        return false;
    }
    
    public void guardarDatosCaseta(AdminFlota adminFlota, String nit, String nombreEmpresa, double valorCanonArrendamiento,
            int cantidadPlazasEstacionamiento){
        
            this.adminFlota = adminFlota;
            this.nit = nit;
            this.nombreEmpresa = nombreEmpresa;
            this.valorCanonArrendamiento = valorCanonArrendamiento;
            this.cantidadPlazasEstacionamiento = cantidadPlazasEstacionamiento;
    }
    
    public void eliminarDatosCaseta(){
            this.adminFlota = null;
            this.nit = null;
            this.nombreEmpresa = null;
            this.valorCanonArrendamiento = 0;
            this.cantidadPlazasEstacionamiento = 0;
    }

    public Bus buscarBus(String placa) {
        for (int i = 0; i < listaBuses.size(); i++) {
            Bus bus = listaBuses.get(i);
            if (bus.getPlaca().equals(placa)) {
                return bus;
            }
        }
        return null;
    }
    
    public void agregarBus(Bus bus) throws SinPlazasEstacionamientoException, BusExisteException {
        Bus aux = buscarBus(bus.getPlaca());
        
        if(aux == null && listaBuses.size() == cantidadPlazasEstacionamiento){
            throw new SinPlazasEstacionamientoException();
        }
        
        if (aux == null) {
            listaBuses.add(bus);
            return;
        }
        
        throw new BusExisteException();
    }
    
    public boolean validarcruzeViajeBus(Viaje viajeNuevo) {
        for (int i = 0; i < listaViajes.size(); i++) {
            Viaje viaje = listaViajes.get(i);
            if (viaje.validarCruceFechas(viajeNuevo) && 
                    viaje.getBus().getPlaca().equals(viajeNuevo.getBus().getPlaca())) {
                
                return true;
            }
        }
        return false;
    }
    
    public Viaje buscarViaje(String fechaSalida, String horaSalida, String fechaLlegada, String horaLlegada, String placa) {
        String codigo = fechaSalida + horaSalida + fechaLlegada + horaLlegada + placa;
        for (int i = 0; i < listaViajes.size(); i++) {
            Viaje viaje = listaViajes.get(i);
            if (viaje.getCodigo().equals(codigo)) {
                return viaje;
            }
        }
        return null;
    }
    
    public Viaje buscarViajePorCodigo(String codigo) {
        for (int i = 0; i < listaViajes.size(); i++) {
            Viaje viaje = listaViajes.get(i);
            if (viaje.getCodigo().equals(codigo)) {
                return viaje;
            }
        }
        return null;
    }
    
    public void agregarViaje(Viaje viaje) throws BusInhabilitadoException {
        if(validarcruzeViajeBus(viaje)){
            throw new BusInhabilitadoException();
        }
        
        listaViajes.add(viaje);
    }
    
    public void agregarVentaTiquetes(Cliente cliente, Tiquete tiquete, Viaje viaje, int cantidadTiquetes) throws SinCuposDisponiblesException, LimiteBusException {
        Viaje aux = buscarViaje(viaje.getFechaSalida(), viaje.getHoraSalida(),
                viaje.getFechaLlegada(), viaje.getHoraLLegada(), viaje.getBus().getPlaca());
        
        if(aux != null && aux.getBus().getPuestosDisponibles() < cantidadTiquetes){
            throw new LimiteBusException();
        }
        
        if(aux != null && aux.calcularCuposDisponibles() < cantidadTiquetes){
            
            String mensajeCola = "";
            if(aux.getBus().getPuestosDisponibles() >= cantidadTiquetes){
                ColaCliente colaCliente = new ColaCliente(aux.getCodigo(), cliente, cantidadTiquetes);
                aux.agregarColaCliente(colaCliente);
                mensajeCola = "Se le asigno a la cola de espera y se le notificara cuando el bus tenga puestos disponibles";
            }
            
            throw new SinCuposDisponiblesException(mensajeCola);
        }
        
        if(aux != null){
            
            RegistroVentas registro = new RegistroVentas("COMPRA", viaje, cantidadTiquetes);
            cliente.agregarRegistroCompra(registro, viaje.getDestino());
            
            tiquete.setCliente(cliente);
            aux.ventaTiquetes(tiquete, cantidadTiquetes);
        }
    }
    
    public void agregarVentaTiquetesConPuntos(Cliente cliente, Tiquete tiquete, Viaje viaje, int cantidadTiquetes) throws SinCuposDisponiblesException, LimiteBusException, 
            SinPuntosAcumuladosException, ValorTiqueteMaximoPuntosException {
        
        Viaje aux = buscarViaje(viaje.getFechaSalida(), viaje.getHoraSalida(),
                viaje.getFechaLlegada(), viaje.getHoraLLegada(), viaje.getBus().getPlaca());
        
        if(aux != null && aux.getBus().getPuestosDisponibles() < cantidadTiquetes){
            throw new LimiteBusException();
        }
        
        if(aux != null && aux.calcularCuposDisponibles() < cantidadTiquetes){
            throw new SinCuposDisponiblesException();
        }
        
        if(aux != null){
            
            RegistroVentas registro = new RegistroVentas("COMPRA_PUNTOS", viaje, cantidadTiquetes);
            cliente.agregarRegistroCompraConPuntos(registro, viaje.getDestino());
            
            tiquete.setCliente(cliente);
            aux.ventaTiquetes(tiquete, cantidadTiquetes);
        }
    }
    
    public void confirmarReserva(Cliente cliente, Viaje viaje) throws ReservaExisteException{
        
        Viaje aux = buscarViaje(viaje.getFechaSalida(), viaje.getHoraSalida(),
                viaje.getFechaLlegada(), viaje.getHoraLLegada(), viaje.getBus().getPlaca());
        
        Reserva reserva = aux.buscarReservaActivas(aux.getCodigo(), cliente.getCedula());
        if(reserva == null){
            throw new ReservaExisteException();
        }
        aux.desactivarReserva(aux.getCodigo(), cliente.getCedula());
        
        RegistroVentas registro = new RegistroVentas("COMPRA_RESERVA", viaje, reserva.getCantidadTiquetes());
        cliente.agregarRegistroCompra(registro, viaje.getDestino());

        Tiquete tiquete = new Tiquete(LocalDate.now());
        tiquete.setCliente(cliente);
        aux.ventaTiquetes(tiquete, reserva.getCantidadTiquetes());
    }
    
    public Notificacion devolucion(Cliente cliente, Viaje viaje) throws TiqueteExisteException{
        
        Viaje aux = buscarViaje(viaje.getFechaSalida(), viaje.getHoraSalida(),
                viaje.getFechaLlegada(), viaje.getHoraLLegada(), viaje.getBus().getPlaca());
        
        Tiquete tiquete = aux.buscarTiquete(cliente.getCedula());
        if(tiquete == null){
            throw new TiqueteExisteException();
        }
        
        aux.eliminarTiquete(cliente.getCedula());
        
        RegistroVentas registro = new RegistroVentas("DEVOLUCION", viaje, 1);
        cliente.descontarPuntosDevolucion(registro);
        
        Devolucion devolucion = new Devolucion(tiquete, LocalDateTime.now(), viaje.getValorUnitario());
        listaDevoluciones.add(devolucion);
        
        return aux.verificarCola();
    }
    
    public void agregarDevolucion(Devolucion devolucion){
        this.listaDevoluciones.add(devolucion);
    }
    
    public Notificacion cancelarReservas(Cliente cliente, Viaje viaje){
        Viaje aux = buscarViaje(viaje.getFechaSalida(), viaje.getHoraSalida(),
                viaje.getFechaLlegada(), viaje.getHoraLLegada(), viaje.getBus().getPlaca());
        aux.cancelarReservas(cliente);
        return aux.verificarCola();
    }
    
    public void crearReservas(Reserva reserva, Viaje viaje) throws LimiteBusException, SinCuposDisponiblesException{
        
        Viaje aux = buscarViaje(viaje.getFechaSalida(), viaje.getHoraSalida(),
                viaje.getFechaLlegada(), viaje.getHoraLLegada(), viaje.getBus().getPlaca());
        
        if(aux != null && aux.getBus().getPuestosDisponibles() < reserva.getCantidadTiquetes()){
            throw new LimiteBusException();
        }
        
        if(aux != null && aux.calcularCuposDisponibles() < reserva.getCantidadTiquetes()){
            throw new SinCuposDisponiblesException();
        }
        
        aux.crearReserva(reserva);
    }
    
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    
    public double getValorCanonArrendamiento() {
        return valorCanonArrendamiento;
    }

    public void setValorCanonArrendamiento(double valorCanonArrendamiento) {
        this.valorCanonArrendamiento = valorCanonArrendamiento;
    }

    public int getCantidadPlazasEstacionamiento() {
        return cantidadPlazasEstacionamiento;
    }

    public void setCantidadPlazasEstacionamiento(int cantidadPlazasEstacionamiento) {
        this.cantidadPlazasEstacionamiento = cantidadPlazasEstacionamiento;
    }

    public AdminFlota getAdminFlota() {
        return adminFlota;
    }

    public void setAdminFlota(AdminFlota adminFlota) {
        this.adminFlota = adminFlota;
    }

    public Lista<Bus> getListaBuses() {
        return listaBuses;
    }

    public void setListaBuses(Lista<Bus> listaBuses) {
        this.listaBuses = listaBuses;
    }
    
    public void setListaViajes(Lista<Viaje> listaViajes) {
        this.listaViajes = listaViajes;
    }

    public Lista<Viaje> getListaViajes() {
        return listaViajes;
    }
    
    public Lista<Devolucion> getListaDevoluciones() {
        return listaDevoluciones;
    }

    public void setListaDevoluciones(Lista<Devolucion> listaDevoluciones) {
        this.listaDevoluciones = listaDevoluciones;
    }

}
