/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Excepciones.SinPuntosAcumuladosException;
import Excepciones.ValorTiqueteMaximoPuntosException;
import Util.Lista;
import java.io.Serializable;


/**
 *
 * @author Valentina
 */
public class Cliente extends Usuario implements Serializable{
    
    private int puntos;
    private Lista<RegistroVentas> registroCompras;
    private Lista<Notificacion> notificaciones;
    

    public Cliente(int puntos, String nombre, String cedula, String correo, String numTel, String tipoUsuario, String contraseña) {
        super(nombre, cedula, correo, numTel, tipoUsuario, contraseña);
        this.puntos = puntos;
        registroCompras = new Lista<>();
        notificaciones = new Lista<>();
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void agregarRegistroCompra(RegistroVentas ventaTiquete, String destino){
        int nuevosPuntos = calcularPuntosAcumulados(ventaTiquete);
        this.puntos += nuevosPuntos;
        
        ventaTiquete.setAccion("+");
        ventaTiquete.setPuntos(nuevosPuntos);
        registroCompras.add(ventaTiquete);
    }
    
    public void agregarRegistroCompraConPuntos(RegistroVentas ventaTiquete, String destino) throws SinPuntosAcumuladosException, ValorTiqueteMaximoPuntosException{
        if(puntos < 90){
            throw new SinPuntosAcumuladosException();
        }
        
        if(ventaTiquete.getViaje().getValorUnitario() > 30000){
            throw new ValorTiqueteMaximoPuntosException();
        }
        
        this.puntos -= 90;
        
        ventaTiquete.setAccion("-");
        ventaTiquete.setPuntos(90);
        registroCompras.add(ventaTiquete);
    }
    
    public int calcularPuntosAcumulados(RegistroVentas ventaTiquete){
        double total = ventaTiquete.totalTiquetes();
        int puntos = 0;
        if(total >= 10000 ){
            puntos = (int) (total / 10000) * 3;
        }
        return puntos;
    }
    
    public void agregarNotificacion(Notificacion notificacion){
        notificaciones.add(notificacion);
    }
    
    public void descontarPuntosDevolucion(RegistroVentas ventaTiquete){
        int puntosGenerados = calcularPuntosAcumulados(ventaTiquete);
        this.puntos -= puntosGenerados;
        
        ventaTiquete.setAccion("-");
        ventaTiquete.setPuntos(puntosGenerados);
        registroCompras.add(ventaTiquete);
    }
    
    public RegistroVentas obtenerUltimoRegistroCompra(){
        return registroCompras.get(registroCompras.size() - 1);
    }

    public Lista<RegistroVentas> getRegistroCompras() {
        return registroCompras;
    }

    public Lista<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(Lista<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    
    
}