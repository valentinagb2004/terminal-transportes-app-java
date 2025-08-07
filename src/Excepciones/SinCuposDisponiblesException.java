/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author Valentina
 */
public class SinCuposDisponiblesException extends Exception{

    public SinCuposDisponiblesException() {
        super("La cantidad de tiquetes sobrepasa los cupos disponibles");
    }
    
     public SinCuposDisponiblesException(String mensaje) {
        super("La cantidad de tiquetes sobrepasa los cupos disponibles. "+mensaje);
    }
    
}
