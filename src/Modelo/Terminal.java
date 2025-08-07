/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Excepciones.ClienteExisteException;
import Util.Lista;
import java.io.Serializable;

/**
 *
 * @author Valentina
 */
public class Terminal implements Serializable{
    
    private Caseta[][] casetas;
    private Lista<Cliente> listaClientes;
    private AdminTerminal adminTerminal;
    private Usuario usuariologin;

    public Terminal() {
        this.listaClientes = new Lista<>();
        adminTerminal = new AdminTerminal("Armenia", "admin", "admin", "admin", "admin", "ADMIN_TERMINAL", "admin");
        initCasetas();
    }
    
    private void initCasetas() {
        casetas = new Caseta[4][];
        casetas [0] = new Caseta [5];
        casetas [1] = new Caseta [2];
        casetas [2] = new Caseta [2];
        casetas [3] = new Caseta [2];
        
        for (int i = 0; i < casetas.length; i++) {
            for (int j = 0; j < casetas[i].length; j++) {
                Caseta caseta = new Caseta();
                casetas[i][j] = caseta;
            }
        }
    }
    
    public Usuario loginUsuario(String cedula, String contrasena){
        
        if(adminTerminal.getCedula().equals(cedula) && adminTerminal.getContraseña().equals(contrasena)){
            usuariologin = adminTerminal;
            return adminTerminal;
        }
        
        for (int i = 0; i < casetas.length; i++) {
            for (int j = 0; j < casetas[i].length; j++) {
                Caseta caseta = casetas[i][j];
                AdminFlota adminFlota = caseta.getAdminFlota();
                if(adminFlota != null && adminFlota.getCedula().equals(cedula) && adminFlota.getContraseña().equals(contrasena)){
                    usuariologin = adminFlota;
                    return adminFlota;
                }
            }
        }
        
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente cliente = listaClientes.get(i);
            if(cliente.getCedula().equals(cedula) && cliente.getContraseña().equals(contrasena)){
                 usuariologin = cliente;
                return cliente;
            }
        }
        
        usuariologin = null;
        return null;
    }
    
    public boolean verificarExistenciaAdminFlota(String cedula){
        for (int i = 0; i < casetas.length; i++) {
            for (int j = 0; j < casetas[i].length; j++) {
                Caseta caseta = casetas[i][j];
                AdminFlota adminFlota = caseta.getAdminFlota();
                if(adminFlota != null && adminFlota.getCedula().equals(cedula)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean verificarExistenciaEmpresa(String nit){
        for (int i = 0; i < casetas.length; i++) {
            for (int j = 0; j < casetas[i].length; j++) {
                Caseta caseta = casetas[i][j];
                if(caseta.getNit() != null && caseta.getNit().equals(nit)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public Lista<Caseta> listarCasetas(){
        Lista<Caseta> lista = new Lista<>();
        for (int i = 0; i < casetas.length; i++) {
            for (int j = 0; j < casetas[i].length; j++) {
                Caseta caseta = casetas[i][j];
                if(caseta != null  && caseta.getAdminFlota() != null){
                    lista.add(caseta);
                }
            }
        }
        return lista;
    }
    
    public Caseta buscarCaseta(String cedula){
        for (int i = 0; i < casetas.length; i++) {
            for (int j = 0; j < casetas[i].length; j++) {
                Caseta caseta = casetas[i][j];
                AdminFlota adminFlota = caseta.getAdminFlota();
                if(adminFlota != null && adminFlota.getCedula().equals(cedula)){
                    return caseta;
                }
            }
        }
        return null;
    }
    
    public Caseta buscarCasetaPorBus(String placa){
        for (int i = 0; i < casetas.length; i++) {
            for (int j = 0; j < casetas[i].length; j++) {
                Caseta caseta = casetas[i][j];
               
                 for (int k = 0; k < caseta.getListaBuses().size(); k++) {
                    Bus aux = caseta.getListaBuses().get(k);
                    if(aux.getPlaca().equals(placa)){
                        return caseta;
                    }
                }
            }
        }
        return null;
    }
    
    public void registrarCliente(Cliente cliente) throws ClienteExisteException {
        Cliente aux = buscarCliente(cliente.getCedula());
        
        if (aux == null) {
            listaClientes.add(cliente);
            return;
        }
        throw new ClienteExisteException();
    }
    
    public Cliente buscarCliente(String cedula){
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente cliente = listaClientes.get(i);
            if(cliente.getCedula().equals(cedula)){
                return cliente;
            }
        }
        return null;
    }
    
    public boolean verificarExistenciaPlaca(String placa){
        for (int i = 0; i < casetas.length; i++) {
            for (int j = 0; j < casetas[i].length; j++) {
                Caseta caseta = casetas[i][j];
                
                Bus bus = caseta.buscarBus(placa);
                if(bus != null){
                    return true;
                }
            }
        }
        return false;
    }
    
    public Lista<Viaje> listaTodosViajesDisponibles(String fechaSalida, String destino) {
        
        Lista<Viaje> lista = new Lista<>();
        for (int i = 0; i < casetas.length; i++) {
            for (int j = 0; j < casetas[i].length; j++) {
                Caseta caseta = casetas[i][j];

                for (int k = 0; k < caseta.getListaViajes().size(); k++) {
                    Viaje viaje = caseta.getListaViajes().get(k);

                    if (viaje.calcularCuposDisponibles() > 0) {

                        // Aplicar los filtros opcionales
                        boolean coincideFecha = (fechaSalida == null || fechaSalida.isEmpty() || viaje.getFechaSalida().equals(fechaSalida));
                        boolean coincideDestino = (destino == null || destino.isEmpty() || viaje.getDestino().equals(destino));

                        if (coincideFecha && coincideDestino) {
                            lista.add(viaje);
                        }
                    }
                }
            }
        }

        return lista;
    }
    
    public Lista<Reserva> listaTodasMisReservasActivas(String cedula) {
        
        Lista<Reserva> lista = new Lista<>();
        for (int i = 0; i < casetas.length; i++) {
            for (int j = 0; j < casetas[i].length; j++) {
                Caseta caseta = casetas[i][j];

                for (int k = 0; k < caseta.getListaViajes().size(); k++) {
                    Viaje viaje = caseta.getListaViajes().get(k);

                    for (int l = 0; l < viaje.getReservas().size(); l++) {
                           Reserva reserva = viaje.getReservas().get(l);
                           if(reserva.getCliente().getCedula().equals(cedula) && reserva.isEstado()){
                               lista.add(reserva);
                           }
                    }
                }
            }
        }

        return lista;
    }
    
    public Lista<Devolucion> listaDevolucionesPorCedula(String cedula) {
        
        Lista<Devolucion> lista = new Lista<>();
        for (int i = 0; i < casetas.length; i++) {
            for (int j = 0; j < casetas[i].length; j++) {
                Caseta caseta = casetas[i][j];

                for (int k = 0; k < caseta.getListaDevoluciones().size(); k++) {
                    Devolucion devolucion = caseta.getListaDevoluciones().get(k);
                    if(devolucion.getTiquetes().getCliente().getCedula().equals(cedula)){
                        lista.add(devolucion);
                    }
                }
            }
        }

        return lista;
    }
    
    public Caseta entregarCaseta(int fila, int columna) {
        return casetas[fila][columna];
    }

    public Usuario getUsuariologin() {
        return usuariologin;
    }
    
    public String getCiudadTerminal(){
        return adminTerminal.getCiudad();
    }
}
