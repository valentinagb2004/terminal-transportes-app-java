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
public class AdminFlota extends Usuario implements Serializable{

    private double sueldo;
    private String telefonoEmpresa;
    private int edad;

    public AdminFlota(double sueldo, String telefonoEmpresa, int edad, String nombre, String cedula, String correo, String numTel, String tipoUsuario, String contraseña) {
        super(nombre, cedula, correo, numTel, tipoUsuario, contraseña);
        this.sueldo = sueldo;
        this.telefonoEmpresa = telefonoEmpresa;
        this.edad = edad;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
     
}
