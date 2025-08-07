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
public class Notificacion implements Serializable{
    
    private String mensaje;
    private LocalDateTime fecha;
    private String cedula;

    public Notificacion(String mensaje, LocalDateTime fecha, String cedula) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.cedula = cedula;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

     
}
