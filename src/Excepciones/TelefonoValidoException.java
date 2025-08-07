/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author valentina
 */
public class TelefonoValidoException extends Exception{

    public TelefonoValidoException() {
        super("Ingrese un telefono valido, debe tener 10 digitos");
    }
    
    
    
}
