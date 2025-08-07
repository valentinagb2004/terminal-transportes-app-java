/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Valentina
 */
public class ColaCliente implements Serializable{
    
    private String codigoViaje;
    private Cliente cliente;
    private int cantidadTiquetes;

    public ColaCliente(String codigoViaje, Cliente cliente, int cantidadTiquetes) {
        this.codigoViaje = codigoViaje;
        this.cliente = cliente;
        this.cantidadTiquetes = cantidadTiquetes;
    }

    public String getCodigoViaje() {
        return codigoViaje;
    }

    public void setCodigoViaje(String codigoViaje) {
        this.codigoViaje = codigoViaje;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getCantidadTiquetes() {
        return cantidadTiquetes;
    }

    public void setCantidadTiquetes(int cantidadTiquetes) {
        this.cantidadTiquetes = cantidadTiquetes;
    }
    
}
