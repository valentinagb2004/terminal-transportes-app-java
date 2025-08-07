/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Util.Cola;
import Util.Lista;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Valentina
 */
public class Viaje implements Serializable{
    
    private String origen;
    private String destino;
    private String  fechaSalida;
    private String fechaLlegada;
    private LocalDate fechaCreacion;
    private String horaSalida;
    private String horaLLegada;
    private Bus bus;
    private double valorUnitario;  
    private String codigo;
    
    private LocalDateTime fechaSalidaCompleta;
    private LocalDateTime fechaLlegadaCompleta;
    
    private Lista<Tiquete> tiquetes = new Lista<>();
    private Lista<Reserva> reservas;
    private Cola<ColaCliente> colaClientes;

    public Viaje(String origen, String destino, String fechaSalida, String fechaLlegada, String horaSalida, String horaLLegada, Bus bus, double valorUnitario) {
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.horaSalida = horaSalida;
        this.horaLLegada = horaLLegada;
        this.bus = bus;
        this.valorUnitario = valorUnitario;
        this.fechaCreacion = LocalDate.now();
        
        DateTimeFormatter formatoCompleto = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.fechaSalidaCompleta = LocalDateTime.parse(fechaSalida+" "+horaSalida+":00", formatoCompleto);
        this.fechaLlegadaCompleta = LocalDateTime.parse(fechaLlegada+" "+horaLLegada+":00", formatoCompleto);
        this.codigo = fechaSalida+horaSalida+fechaLlegada+horaLLegada+bus.getPlaca();
        
        tiquetes = new Lista<>();
        reservas = new Lista<>();
        colaClientes = new Cola<>();
    }
    
    public boolean validarCruceFechas(Viaje viajeNuevo) {
        // Fecha y hora en la que el bus estará disponible nuevamente (un día después de la llegada de este viaje)
        LocalDateTime fechaHabilitacion = this.fechaLlegadaCompleta.plusDays(1);

        // Verificar que el nuevo viaje no empiece antes de que el bus esté habilitado
        if (viajeNuevo.getFechaSalidaCompleta().isBefore(fechaHabilitacion)) {
            return true;
        }

        // Verificar que el nuevo viaje no se solape con el actual
        boolean solapamientoDeHoras =
            (viajeNuevo.getFechaSalidaCompleta().isBefore(this.fechaLlegadaCompleta) &&
             viajeNuevo.getFechaLlegadaCompleta().isAfter(this.fechaSalidaCompleta));

        return solapamientoDeHoras;
    }
    
    public void ventaTiquetes(Tiquete tiquete, int cantidadTiquetes){
        for (int i = 0; i < cantidadTiquetes; i++) {
            this.tiquetes.add(tiquete);
        }
    }
    
    public int calcularCuposDisponibles(){
        int resultado = bus.getPuestosDisponibles() - tiquetes.size();
        resultado = resultado - calcularReservasActivas();
        return resultado;
    }
    
    public int calcularReservasActivas(){
        int contador = 0;
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            if(reserva.isEstado()){
                contador+= reserva.getCantidadTiquetes();
            }
        }
        
        return contador;
    }
    
    public void agregarColaCliente(ColaCliente cliente){
        colaClientes.enqueue(cliente);
    }
    
    public void cancelarReservas(Cliente cliente){
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            if(reserva.isEstado() && reserva.getCliente().getCedula().equals(cliente.getCedula())){
                reserva.setEstado(false);
            }
        }
    }
    
    public void crearReserva(Reserva reserva){
        reservas.add(reserva);
    }
    
    public Lista<Reserva> obtenerReservasActivas(){
        Lista<Reserva> lista = new Lista<>();
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            if(reserva.isEstado()){
                lista.add(reserva);
            }
        }
        return lista;
    }
    
    public Reserva buscarReservaActivas(String codigo, String cedula){
        for (int i = 0; i < reservas.size(); i++) {
            Reserva aux = reservas.get(i);
            if(aux.isEstado() && codigo.equals(aux.getCodigoViaje()) && cedula.equals(aux.getCliente().getCedula())){
                return aux;
            }
        }
        return null;
    }
    
    public void desactivarReserva(String codigo, String cedula){
        for (int i = 0; i < reservas.size(); i++) {
            Reserva aux = reservas.get(i);
            if(codigo.equals(aux.getCodigoViaje()) && cedula.equals(aux.getCliente().getCedula())){
                aux.setEstado(false);
            }
        }
    }
    
    public Tiquete buscarTiquete(String cedula){
        for (int i = 0; i < tiquetes.size(); i++) {
            Tiquete aux = tiquetes.get(i);
            if(cedula.equals(aux.getCliente().getCedula())){
                return aux;
            }
        }
        return null;
    }
    
    public void eliminarTiquete(String cedula){
        for (int i = 0; i < tiquetes.size(); i++) {
            Tiquete aux = tiquetes.get(i);
            if(cedula.equals(aux.getCliente().getCedula())){
                tiquetes.remove(i);
                break;
            }
        }
    }
    
    public Notificacion verificarCola(){
        if(colaClientes.size() > 0){
            ColaCliente item = colaClientes.peek();
            
            if(this.calcularCuposDisponibles() >= item.getCantidadTiquetes()){
            
                Reserva reserva = new Reserva(this.codigo, item.getCliente(), true, LocalDateTime.now(), item.getCantidadTiquetes());
                crearReserva(reserva);
                colaClientes.dequeue();
                String mensaje = "Se le asigno una reserva en el bus con placa " + bus.getPlaca() +" para "+destino + " a la persona que estaba en cola cc:"+item.getCliente().getCedula();
                Notificacion notificacion = new Notificacion(mensaje, LocalDateTime.now(),item.getCliente().getCedula());
                return notificacion;
            }
        }
        return null;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHoraLLegada() {
        return horaLLegada;
    }

    public void setHoraLLegada(String horaLLegada) {
        this.horaLLegada = horaLLegada;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public LocalDateTime getFechaSalidaCompleta() {
        return fechaSalidaCompleta;
    }

    public void setFechaSalidaCompleta(LocalDateTime fechaSalidaCompleta) {
        this.fechaSalidaCompleta = fechaSalidaCompleta;
    }

    public LocalDateTime getFechaLlegadaCompleta() {
        return fechaLlegadaCompleta;
    }

    public void setFechaLlegadaCompleta(LocalDateTime fechaLlegadaCompleta) {
        this.fechaLlegadaCompleta = fechaLlegadaCompleta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Lista<Reserva> getReservas() {
        return reservas;
    }
    
    
}
