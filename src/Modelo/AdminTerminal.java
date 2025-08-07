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
public class AdminTerminal extends Usuario implements Serializable{    

    private String ciudad;

    public AdminTerminal(String ciudad, String nombre, String cedula, String correo, String numTel, String tipoUsuario, String contraseña) {
        super(nombre, cedula, correo, numTel, tipoUsuario, contraseña);
        this.ciudad = ciudad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
}
