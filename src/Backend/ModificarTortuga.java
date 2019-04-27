/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Frontend.Panel;
import Models.Identificador;
import Parser.Analizador_Lexico;
import Parser.parser;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author anclenius
 */
public class ModificarTortuga {
    private static Tortuga tortuga;
    private static List<Identificador> tablaIdentificadores = new ArrayList<>();
    public ModificarTortuga(Tortuga currentTurtle){
        tortuga = currentTurtle;
    }
    
    
    public static void moveForward(String n){
        Panel.Console.append("La tortuga se movio " + n + " pasos hacia adelante\n");
        tortuga.moveForward(Integer.parseInt(n));
    }
    
    public static void moveBackward(String n){
        Panel.Console.append("La tortuga se movio " + n + " pasos hacia atras\n");
        tortuga.moveBackward(Integer.parseInt(n));
    }
    
    public static void moveRight(String n){
        Panel.Console.append("La tortuga se movio " + n + " grados hacia la derecha\n");
        tortuga.moveRight(Integer.parseInt(n));
    }
    
    public static void moveLeft(String n){
        Panel.Console.append("La tortuga se movio " + n + " grados hacia la izquierda\n");
        tortuga.moveLeft(Integer.parseInt(n));
    }
    
    public static void penDown(){
        Panel.Console.append("La pluma esta escribiendo\n");
        tortuga.penDown();
    }
    
    public static void penUp(){
        Panel.Console.append("La pluma se levanto\n");
        tortuga.penUp();
    }
    
    public static void showTurtle(){
        Panel.Console.append("La tortuga salio\n");
        tortuga.showTurtle();
    }
    
    public static void hideTurtle(){
        Panel.Console.append("La tortuga se escondio\n");
        tortuga.hideTurtle();
    }
    
    public static void setColor(String n){
        Panel.Console.append("Se cambio de color\n");
        tortuga.color(n);
    }
    
    public static void positionxy(String x,String y){
        Panel.Console.append("Cambie de posicion\n");
        tortuga.positionxy(Integer.parseInt(x), Integer.parseInt(y));
    }
    
    public static void positionx(String x){
        Panel.Console.append("La tortuga cambio de posicion horizontalmente\n");
        tortuga.positionx(Integer.parseInt(x));
    }
    
    public static void positiony(String y){
        Panel.Console.append("La tortuga cambio de posicion verticalmente\n");
        tortuga.positiony(Integer.parseInt(y));
    }
    
    public static void toErase(){
        Panel.Console.append("La tortuga ha empezado a borrar\n");
       tortuga.toErase();
    }
    
    public static void toDraw(){
        Panel.Console.append("La tortuga ha empezado a escribir\n");
        tortuga.toDraw();
    }
    
    public static void clears(){
        Panel.Console.append("Se reinicio el mundo\n");
        tablaIdentificadores.clear();
        tortuga.clears();
    }
    
    public static void toCenter(){
        Panel.Console.append("La tortuga regreso al centro\n");
        tortuga.toCenter();
    }
    
    public static float evaluateExpression(String expression) throws ScriptException{
        ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("js");
            //System.out.println("Expresion a evaluar: " + expresionAEvaluar);
            for (Identificador id : tablaIdentificadores) {
                if(expression.contains(id.getNombre())){
                    expression = expression.replace(id.getNombre(), id.getValor() + "");
                }
            }
            Object result = engine.eval(expression);
            String result2 = result.toString();
            float result3 = Float.parseFloat(result2);
            return result3;
            
    }
    
    public static void assignID(String nombre,String value){
        float valor = 0;
        try {
            valor = evaluateExpression(value);
        } catch (ScriptException ex) {
            Panel.Console.append("Oye, ese identificador no existe\n");
        }
        boolean flag = false;
        for (Identificador id : tablaIdentificadores) {
            if(id.getNombre().equals(nombre)){
                flag = true;
            }
        }
        
        if(!flag){
            tablaIdentificadores.add(new Identificador(nombre,valor));
            Panel.Console.append("Agregaste el numero " + valor + " en el identificador " + nombre + "\n");
        } else {
             Panel.Console.append("Oye, ese identificador ya existe\n");
        }
        
    }
    
    public static String repeat(String text, String value){
        float na;
        int n;
        String Text = "";
        try {
            na = evaluateExpression(value);
            n = (int) na;
            for(int i = 0; i<n;i++){
                Text = Text + "\n" + text;
            }
        } catch (ScriptException ex) {
            Panel.Console.append("Oye, ese identificador no existe\n");
        }
        
        return Text;
    }
    
    public static void executeRepeat(String text){
        StringReader reader = new StringReader(text);
                Analizador_Lexico miLexer = new Analizador_Lexico(reader);
                parser miParser = new parser(miLexer) {};
                try {
                    System.out.println("Empezando a parsear ciclo");
                    miParser.parse();
                    
                } catch (Exception ex) {
                    System.out.println("Comando desconocido.");
                    ex.printStackTrace();
                }
    }
}
