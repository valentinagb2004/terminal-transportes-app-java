/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author Valentina
 */
public class LimiteBusException extends Exception{

    public LimiteBusException() {
        super("Sobrepasa el limite del bus");
    }
}
