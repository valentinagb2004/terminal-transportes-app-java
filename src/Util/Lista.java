/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.io.Serializable;

/**
 *
 * @author Valentina
 */
public class Lista <S> implements Ilist<S>, Serializable {
    
    private Nodo<S> primero;
    private  int size;
    
    public Lista(){
        this.primero = null; 
        this.size = 0;
    }
    
    @Override
    public void add(S dato, int index) {
        
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        
        Nodo nuevo = new Nodo(dato);
        if (index == 0) {
            nuevo.siguiente = primero; 
            primero = nuevo; 
        } else {
            Nodo aux = primero;
            int contador = 0;

            while (contador < index - 1) {
                aux = aux.siguiente;
                contador++;
            }

            nuevo.siguiente = aux.siguiente; 
            aux.siguiente = nuevo;
        }
        size++;
    }
    
    
    @Override
    public S get(int index){
        
        if(index <0 || index >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
        
        if(index == 0){
            return primero.dato;
        }else{
            Nodo <S> aux = primero;
            int contador = 0;
            while(contador < index){
                aux = aux.siguiente;
                contador++;
            }
            return aux.dato;
        }
    }
    
    
    @Override
    public boolean remove(int index) {
        
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (index == 0) {
            primero = primero.siguiente;
        } else {

            Nodo aux = primero;
            int contador = 0;
            while (contador < index - 1) {
                aux = aux.siguiente;
                contador++;
            }

            Nodo nodoAEliminar = aux.siguiente;
            aux.siguiente = nodoAEliminar.siguiente;
        }
        size--; 
        return true;
    }
    
    @Override
    public int size(){
        return this.size;
    }
    
    @Override
    public void clear() {
        primero = null; 
        size = 0;      
    }

    @Override
    public boolean isEmpty() {
        return primero == null;
    }

    @Override
    public void add(S dato){
        Nodo<S> nuevo = new Nodo<S> (dato);
        if(primero == null){
            primero = nuevo;
        }else {
            Nodo<S> aux = primero;
            while(aux.siguiente!= null){
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
        size++;
    }
}