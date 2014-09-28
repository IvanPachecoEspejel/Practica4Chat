/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utileria;

/**
 *Esta clase nos ayuda a traducir los tipos de banderas y codigos que se utilizan en el sistema
 * @author ivan
 */
public class Traducir {
    
    public static String etiqueta = "<img WIDTH = 10 HEIGHT = 10 SRC = \"";
    public static String d = System.getProperty("line.separator");
    public static String pathProyecto = System.getProperty("user.dir");
    
    /**
     *Metodo para poder traducir un tipo de mensaje
     * @param id id es el tipo de mensaje a traducir
     * @return Regresa la descripcion del tipo de mensaje recibido
     */
    public static String Mensaje(int id){
        if(id == 0)
            return "Mensaje de login";
        if(id == 1)
            return "Mensaje para todos";
        if(id == 2)
            return "Mensaje privado";
        if(id == 4)
            return "Mensaje de logout";
        
        return "Error: Mensaje no reconocido";
    }
    
    /**
     * Metodo que traduce un codigo de emotico a codigo en html
     * @param emotico emotico describe una imagen emotico por medio de una secuencia de caracteres
     * @return Regresa una etiqueta imagen de HTML del emotico
     */
    public static String Emotico(String emotico){
        if(emotico.equals(":)"))
            return etiqueta+pathProyecto+d+"img"+d+"sonriente.jpg\" />";
        
        //Se agregan todos los emoticones necesarios
        return "";
    }
    
}