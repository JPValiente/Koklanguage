/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Color;

/**
 *
 * @author anclenius
 */
public class Coordenadas {
    private int x;
    private int y;
    private Color color;
    private boolean pen;
    
    public Coordenadas(int x,int y,Color color,boolean pen){
        this.x = x;
        this.y = y;
        this.color = color;
        this.pen = pen;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public Color getColor(){
        return color;
    }
    
    public boolean getPen(){
        return pen;
    }
    
    public void penDown(){
        this.pen = true;
    }
}
