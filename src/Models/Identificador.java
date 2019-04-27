/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author anclenius
 */
public class Identificador {
    String nombre;
    float valor;
    
    public Identificador(String nombre,float valor){
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public float getValor() {
        return valor;
    }
    
    
}
