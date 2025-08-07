/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Util;

import java.util.LinkedList;

/**
 *
 * @author Valentina
 */
public interface IQueve <T> {
   
    public void enqueue(T data);
    
    public T dequeue();
    
    public T peek();
    
    public boolean isEmpty();
    
    public int size();
  
}
