/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;
import Modelo.AdminFlota;
import Modelo.Caseta;
import Modelo.Terminal;

/**
 *
 * @author  Valentina
 */
public class ControladorCaseta {

    private Caseta caseta;
    private Terminal terminal;
    
    public ControladorCaseta(Caseta caseta, Terminal terminal) {
        this.caseta = caseta;
        this.terminal = terminal;
    }
    
    public void guardarDatosCaseta(AdminFlota adminFlota, String nit, String nombreEmpresa, double valorCanonArrendamiento,
            int cantidadPlazasEstacionamiento){
        caseta.guardarDatosCaseta(adminFlota, nit, nombreEmpresa, valorCanonArrendamiento, cantidadPlazasEstacionamiento);
    }
    
    public boolean verificarExistenciaAdminFlota(String cedula){
        return terminal.verificarExistenciaAdminFlota(cedula);
    }
    
    public boolean verificarExistenciaEmpresa(String nit){
        return terminal.verificarExistenciaEmpresa(nit);
    }
    
}