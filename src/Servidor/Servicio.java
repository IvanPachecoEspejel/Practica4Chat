/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 *Clase que contiene los metodos necesarios para manejar el comportamiento de un servidor de un chat
 * @author ivan
 */
public class Servicio {
    
    private MulticastSocket ms;
    
    private String HOST_GRUPO;
    private String HOST_LOCAL;
    private int PUERTO;
    
    /**
     * Constructor que inicializa la ip del grupo a unirse y el puerto de comunicación
     * @param grupo grupo indica la dirección ip del grupo al que se unirá el usuario el valor por default es 230.1.1.1
     * @param puerto puerto indica el puerto por el cual se comunicará el usuario el valor por default es 4000
     */
    public Servicio(String grupo, int puerto){
        this.HOST_GRUPO = grupo;
        this.PUERTO = puerto;
        this.HOST_LOCAL = null;
    }
    
    /**
     * Constructor que inicializa la ip del grupo a unirse y el puerto de comunicación al valor por default
     * El valor por default de la ip del grupo es 230.1.1.1
     * El valor por default del puerto es 4000
     */
    public Servicio(){
        this.HOST_GRUPO = "230.1.1.1";
        this.PUERTO = 4000;
        this.HOST_LOCAL = null;
    }    
    
    /**
     * Funcion para poder levantar el servicio de chat con el puerto y direccion IP del grupo establecidos
     * @throws java.io.IOException Lanzara la excepcion IOException cuando no se pueda levantar el servicio en el puerto indicado
     * @throws java.net.UnknownHostException Lanzará ña excepción UnknownHostException cuando no pueda obtener la ip local, o cuando no pueda resolver la ip del grupo
     */
    public void unirAlGrupo() throws IOException, UnknownHostException{
        this.setMs(new MulticastSocket(this.getPUERTO()));
        InetAddress grupo = this.validarDireccionDelGrupo();
        this.getMs().joinGroup(grupo);
        System.out.println("Se ha logrado unir al grupo con la ip : "+this.getHOST_GRUPO()+" con el puerto: "+this.getPUERTO());
    }
    
    /**
     * Función para poder resolver la dirección ip del grupo que se establecio y obtener la ip local
     * @return Un objeto InetAddres que contiene la dirección ip del grupo
     * @throws UnknownHostException Lanza esta excepción cuando no pueda encontrar la ip del grupo
     */
    public InetAddress validarDireccionDelGrupo() throws UnknownHostException{
        this.setHOST_LOCAL(InetAddress.getLocalHost().getHostAddress());
        return InetAddress.getByName(this.getHOST_GRUPO());
    }
    
    public void enviarMensaje(String mensaje){
        
    }

    //<editor-fold desc="Geters y Seters">
    /**
     * @return the ms
     */
    public MulticastSocket getMs() {
        return ms;
    }

    /**
     * @param ms the ms to set
     */
    public void setMs(MulticastSocket ms) {
        this.ms = ms;
    }

    /**
     * @return the HOST_GRUPO
     */
    public String getHOST_GRUPO() {
        return HOST_GRUPO;
    }

    /**
     * @param HOST_GRUPO the HOST_GRUPO to set
     */
    public void setHOST_GRUPO(String HOST_GRUPO) {
        this.HOST_GRUPO = HOST_GRUPO;
    }

    /**
     * @return the PUERTO
     */
    public int getPUERTO() {
        return PUERTO;
    }

    /**
     * @param PUERTO the PUERTO to set
     */
    public void setPUERTO(int PUERTO) {
        this.PUERTO = PUERTO;
    }

    /**
     * @return the HOST_LOCAL
     */
    public String getHOST_LOCAL() {
        return HOST_LOCAL;
    }

    /**
     * @param HOST_LOCAL the HOST_LOCAL to set
     */
    public void setHOST_LOCAL(String HOST_LOCAL) {
        this.HOST_LOCAL = HOST_LOCAL;
    }
    
    //</editor-fold>
    
    public class Cliente implements Runnable{
        
        public Cliente (){
            
        }

        @Override
        public void run() {
            
        }
        
    }
}
