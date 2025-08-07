/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;
import Modelo.Devolucion;
import Modelo.Terminal;
import Util.Lista;

/**
 *
 * @author Valentina
 */
public class ControladorResumen {
   
    private Terminal terminal;
    
    public ControladorResumen(Terminal terminal) {
        this.terminal = terminal;
    }
    
    public Lista<Devolucion> misDevoluciones(String cedula){
        return terminal.listaDevolucionesPorCedula(cedula);
    }
    
}
