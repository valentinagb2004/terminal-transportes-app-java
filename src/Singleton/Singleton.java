/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Singleton;

import Modelo.Terminal;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Valentina
 */
public class Singleton {
    
    private static final Singleton INSTANCIA = new Singleton();
    
    private Singleton() {
    }

    public static Singleton getINSTANCIA() {
        return INSTANCIA;
    }

    public void escribirObjetoTerminal(Terminal terminal){
        try {
            FileOutputStream archivo = new FileOutputStream("terminal.dat");
            ObjectOutputStream escritor = new ObjectOutputStream(archivo);
            escritor.writeObject(terminal);
        } catch (IOException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
           
    public Terminal leerObjetoTerminal(){
        try{
            FileInputStream archivo = new FileInputStream("terminal.dat");
            ObjectInputStream lector = new ObjectInputStream(archivo);
            return  (Terminal) lector.readObject();
        } catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
            return new Terminal();
        }
    }
}
