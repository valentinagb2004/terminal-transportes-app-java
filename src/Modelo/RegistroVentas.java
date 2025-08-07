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
public class RegistroVentas implements Serializable{
    
    private String tipoVenta;//COMPRA, COMPRA_PUNTOS, COMPRA_RESERVA, DEVOLUCION
    private LocalDateTime fechaVenta;
    private Viaje viaje;
    private int cantidadTiquetes;
    private int puntos;
    private String accion;

    public RegistroVentas(String tipoVenta, Viaje viaje,int cantidadTiquetes) {
        this.tipoVenta = tipoVenta;
        this.viaje = viaje;
        this.cantidadTiquetes = cantidadTiquetes;
        this.fechaVenta = LocalDateTime.now();
        this.puntos = 0;
    }
    
    public double totalTiquetes(){
        double total = viaje.getValorUnitario() *  cantidadTiquetes;
        return total;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public int getCantidadTiquetes() {
        return cantidadTiquetes;
    }

    public void setCantidadTiquetes(int cantidadTiquetes) {
        this.cantidadTiquetes = cantidadTiquetes;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    
    
    
}
