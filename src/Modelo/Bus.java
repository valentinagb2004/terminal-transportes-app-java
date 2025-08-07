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
public class Bus implements Serializable{
    
    private String placa;
    private int puestosDisponibles;
    private String color;
    private String marca;
    private String wifi;

    public Bus(String placa, int puestosDisponibles, String color, String marca, String wifi) {
        this.placa = placa;
        this.puestosDisponibles = puestosDisponibles;
        this.color = color;
        this.marca = marca;
        this.wifi = wifi;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getPuestosDisponibles() {
        return puestosDisponibles;
    }

    public void setPuestosDisponibles(int puestosDisponibles) {
        this.puestosDisponibles = puestosDisponibles;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    @Override
    public String toString() {
        return placa;
    }
    
}
