/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;
import Modelo.Caseta;
import Modelo.Cliente;
import Modelo.Terminal;
import Modelo.Usuario;
import Singleton.Singleton;
import Util.Lista;

/**
 *
 * @author Valentina
 */
public class ControladorPrincipal {

    private Terminal terminal;

    public ControladorPrincipal() {
        this.terminal = Singleton.getINSTANCIA().leerObjetoTerminal();
    }
    
    public Cliente buscarCliente(String cedula){
        return terminal.buscarCliente(cedula);
    }
    
    public Lista<Caseta> listarCasetas(){
        return terminal.listarCasetas();
    }
    
    public Caseta buscarCaseta(String cedula){
        return terminal.buscarCaseta(cedula);
    }
    
    public Caseta entregarCaseta(int fila, int columna) {
        return terminal.entregarCaseta(fila, columna);
    }
    
    public Usuario getUsuariologin() {
        return terminal.getUsuariologin();
    }
    
    public boolean verificarExistenciaPlaca(String placa){
        return terminal.verificarExistenciaPlaca(placa);
    }
    
    public String getCiudadTerminal(){
        return terminal.getCiudadTerminal();
    }
    
    public void guardarCambios(){
        Singleton.getINSTANCIA().escribirObjetoTerminal(terminal);
    }   

    public Terminal getTerminal() {
        return terminal;
    }
    
}
