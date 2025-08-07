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
public class Devolucion implements Serializable{
    private Tiquete tiquetes;
    private LocalDateTime fechaDevolucion;
    private double valor;

    public Devolucion(Tiquete tiquetes, LocalDateTime fechaDevolucion, double valor) {
        this.tiquetes = tiquetes;
        this.fechaDevolucion = fechaDevolucion;
        this.valor = valor;
    }

    public Tiquete getTiquetes() {
        return tiquetes;
    }

    public void setTiquetes(Tiquete tiquetes) {
        this.tiquetes = tiquetes;
    }
 
    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
