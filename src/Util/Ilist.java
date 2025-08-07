/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Util;

/**
 *
 * @author Valentina
 */
public interface Ilist <T> {
    
    public void add(T dato);
    
    public T get (int index);
    
    public void add (T dato, int index);
    
    public boolean remove (int index);
    
    public boolean isEmpty ();
    
    public int size ();
    
    public void clear ();
}