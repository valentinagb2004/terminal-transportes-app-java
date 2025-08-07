/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Valentina
 */
public class Reserva  implements Serializable{
    
    private String codigoViaje;
    private Cliente cliente;
    private boolean estado;
    private LocalDateTime fechaReserva;
    private int cantidadTiquetes;

    public Reserva(String codigoViaje, Cliente cliente, boolean estado, LocalDateTime fechaReserva, int cantidadTiquetes) {
        this.codigoViaje = codigoViaje;
        this.cliente = cliente;
        this.estado = estado;
        this.fechaReserva = fechaReserva;
        this.cantidadTiquetes = cantidadTiquetes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public int getCantidadTiquetes() {
        return cantidadTiquetes;
    }

    public void setCantidadTiquetes(int cantidadTiquetes) {
        this.cantidadTiquetes = cantidadTiquetes;
    }

    public String getCodigoViaje() {
        return codigoViaje;
    }

    public void setCodigoViaje(String codigoViaje) {
        this.codigoViaje = codigoViaje;
    }
    
    
}
