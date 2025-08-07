/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author Valentina
 */
public class ValorTiqueteMaximoPuntosException extends Exception{

    public ValorTiqueteMaximoPuntosException() {
        super("El valor maximo del tiquete a cambiar es de 30000, el valor del viaje es mayor a esta cantidad");
    }
    
    
    
}
