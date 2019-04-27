/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Models.Coordenadas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author anclenius
 */
public class Tortuga extends JPanel{
    private final int DEFAULT_X = 480;
    private final int DEFAULT_Y = 200;
    private final int DEFAULT_DEGREE = 270;
    private float currentDegree = DEFAULT_DEGREE;
    private int currentX = DEFAULT_X;
    private int currentY = DEFAULT_Y;
    private String savedColor = "000000";
    private String currentColor = "000000";
    private boolean showTurtle = true;
    private boolean pen = true;
    private List<Coordenadas> coordenadas = new ArrayList<>();        
    public Tortuga(){
        this.setVisible(true);
        this.setSize(965,370);
        this.setBackground(Color.WHITE);
        this.coordenadas.add(new Coordenadas(DEFAULT_X,DEFAULT_Y,returnColor(currentColor),pen));
        //this.paintTurtle(20, 20);
        
    }
    public void reload(){
        this.remove(this);
        this.validate();
        repaint();
    }
    
  
    
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(showTurtle){
            g.setColor(returnColor("000000"));
            //Dibuja el cuerpo
            g.fillOval(currentX, currentY, 16, 16);
            //Dibuja la pata izquierda delantera
            g.drawOval(currentX-3, currentY-1, 5, 5);
            //Dibuja la pata izquierda trasera
            g.drawOval(currentX-3, currentY+10, 5, 5);
            //Dibuja la pata derecha delantera
            g.drawOval(currentX+14, currentY-1, 5, 5);
            //Dibuja la pata derecha trasera
            g.drawOval(currentX+15, currentY+10, 5, 5);
            //Dibuja la cabeza
            g.drawOval(currentX+5, currentY-5, 6, 6);
        } 
        
            for(int i = 1; i<coordenadas.size();i++){
                if(coordenadas.get(i).getPen()){
                    g.setColor(coordenadas.get(i).getColor());
                    g.drawLine(coordenadas.get(i-1).getX(), coordenadas.get(i-1).getY(), coordenadas.get(i).getX(), coordenadas.get(i).getY());
                } 
            }
        
        
        
        
    }
    

    
    public void rePaint(int x,int y){
        currentX = x;
        currentY = y;
        this.coordenadas.add(new Coordenadas(x,y,returnColor(currentColor),pen));
        this.reload();
        
    }
    
    public void moveForward(int n){
        currentX = currentX + (int)(n*Math.cos(currentDegree*Math.PI/180));     
        currentY = currentY + (int)(n*Math.sin(currentDegree*Math.PI/180));    
        this.coordenadas.add(new Coordenadas(currentX,currentY,returnColor(currentColor),pen));
        this.reload();
    }
    
    public void moveBackward(int n){
        currentX = currentX - (int)(n*Math.cos(currentDegree*Math.PI/180));     
        currentY = currentY - (int)(n*Math.sin(currentDegree*Math.PI/180)); 
            this.coordenadas.add(new Coordenadas(currentX,currentY,returnColor(currentColor),pen));
        
        this.reload();
    }
    
    public void moveRight(int n){
        currentDegree = currentDegree + n;
        
        
        this.reload();
    }
    
    public void moveLeft(int n){
        currentDegree = currentDegree - n;
        
        
        this.reload();
    }
    
    public void penDown(){
        
        pen = true;
        this.reload();
    }
    
    public void penUp(){
        pen = false;
        this.reload();
    }
    
    public void showTurtle(){
        showTurtle = true;
        this.reload();
    }
    
    public void hideTurtle(){
        showTurtle = false;
        this.reload();
    }
    
    public void clears(){
        currentX = DEFAULT_X;
        currentY = DEFAULT_Y;
        coordenadas.clear();
        this.coordenadas.add(new Coordenadas(DEFAULT_X,DEFAULT_Y,returnColor(currentColor),pen));
        this.reload();
    }
    
    public void toCenter(){
        currentX = DEFAULT_X;
        currentY = DEFAULT_Y;
        pen = false;
        this.coordenadas.add(new Coordenadas(currentX,currentY,returnColor(currentColor),pen));
        pen = true;
        this.reload();
    }
    
    public void toErase(){
       this.savedColor = this.currentColor;
       this.currentColor = "ffffff";
       this.reload();
    }
    
    public void toDraw(){
        this.currentColor = this.savedColor;
        this.reload();
    }
    
    public void color(String na){
        if(na.contains("#")){
            System.out.println("Es un hexa");
            String n = na.replace("#", "");
            currentColor = n;
        }
        else if(Integer.parseInt(na)>= 0 && Integer.parseInt(na)<10){
            System.out.println("Es numerito");
            currentColor = getColor(Integer.parseInt(na));
        } 
        this.reload();
    }
    
    public void positionxy(int x,int y){
        currentX = x;
        currentY = y;
        this.coordenadas.add(new Coordenadas(currentX,currentY,returnColor(currentColor),pen));
        this.reload();
    }
    
    public void positionx(int x){
        currentX = x;
        this.coordenadas.add(new Coordenadas(currentX,currentY,returnColor(currentColor),pen));
        this.reload();
    }
    
    public void positiony(int y){
        currentY = y;
        this.coordenadas.add(new Coordenadas(currentX,currentY,returnColor(currentColor),pen));
        this.reload();
    }
    
    public String getColor(int n){
        String hexa = "";
        switch (n){
            case 0:
                //Negro
                hexa = "000000";
            break;
            case 1:
                //Rojo
                hexa = "FE2E2E";
            break;
            case 2:
                //Naranja
                hexa = "FAAC58";
            break;
            case 3:
                //Amarillo
                hexa = "F7FE2E";
            break;
            case 4:
                //Verde claro
                hexa = "00FF00";
            break;
            case 5:
                //cyan
                hexa = "2EFEF7";
            break;
            case 6:
                //azul marino
                hexa = "0000FF";
            break;
            case 7:
                //Verde Oscuro
                hexa = "088A08";
            break;
            case 8:
                //Algo azul
                hexa = "610B4B";
            break;
            case 9:
                //Morado
                hexa = "D358F7";
            break;
            default:
                hexa = "000000";
            break;
            
        }
       return hexa;
    }
    
    public Color returnColor(String colorStr){
        return new Color(
            Integer.valueOf( colorStr.substring( 0, 2 ), 16 ),
            Integer.valueOf( colorStr.substring( 2, 4 ), 16 ),
            Integer.valueOf( colorStr.substring( 4, 6 ), 16 ) );
    }
}
