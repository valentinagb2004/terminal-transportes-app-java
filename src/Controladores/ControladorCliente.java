/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Excepciones.ClienteExisteException;
import Modelo.Cliente;
import Modelo.Terminal;
import Modelo.Usuario;

/**
 *
 * @author Valentina
 */
public class ControladorCliente {
    
    private Terminal terminal;

    public ControladorCliente(Terminal terminal) {
        this.terminal = terminal;
    }
    
    public void registrarCliente(Cliente cliente) throws ClienteExisteException{
       terminal.registrarCliente(cliente);
    }
    
    public Usuario loginUsuario(String cedula, String contrasena){
        return terminal.loginUsuario(cedula, contrasena);
    }
       
    
}
