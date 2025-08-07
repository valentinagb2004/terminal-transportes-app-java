/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Valentina
 */
public class Cola <T> implements IQueve<T>, Serializable{
   
    private LinkedList<T> elements;

    public Cola() {
        elements = new LinkedList<>();
    }

    // Implementación de enqueue: añade el elemento al final de la cola
    @Override
    public void enqueue(T data) {
        elements.addLast(data);
    }

    // Implementación de dequeue: remueve y devuelve el primer elemento de la cola
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return elements.removeFirst();
    }

    // Implementación de peek: devuelve el primer elemento sin removerlo
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return elements.getFirst();
    }

    // Implementación de isEmpty: verifica si la cola está vacía
    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }
    
    // Implementación de isEmpty: retorna el tamaño de la cola
    @Override
    public int size() {
        return elements.size();
    }
    

}
