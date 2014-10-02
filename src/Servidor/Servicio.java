/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

import DTO.Mensaje;
import Utileria.Utileria;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 *Clase que contiene los metodos necesarios para manejar el comportamiento de un cliente de un chat
 * @author ivan
 */
public class Servicio {
    
    private MulticastSocket ms;
    private InetAddress grupo;
    
    private String HOST_GRUPO;
    private String HOST_LOCAL;
    private int PUERTO;
    
    private final int TAM_MNSJ;
    
    /**
     * Constructor que inicializa la ip del grupo a unirse y el puerto de comunicación
     * @param grupo grupo indica la dirección ip del grupo al que se unirá el usuario el valor por default es 230.1.1.1
     * @param puerto puerto indica el puerto por el cual se comunicará el usuario el valor por default es 4000
     */
    public Servicio(String grupo, int puerto){
        this.HOST_GRUPO = grupo;
        this.PUERTO = puerto;
        this.HOST_LOCAL = null;
        this.grupo = null;
        this.TAM_MNSJ = 10;
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
        this.grupo = null;
        this.TAM_MNSJ = 10;
    }    
    
    /**
     * Funcion para poder levantar el servicio de chat con el puerto y direccion IP del grupo establecidos
     * @throws java.io.IOException Lanzara la excepcion IOException cuando no se pueda levantar el servicio en el puerto indicado
     * @throws java.net.UnknownHostException Lanzará ña excepción UnknownHostException cuando no pueda obtener la ip local, o cuando no pueda resolver la ip del grupo
     */
    public void unirAlGrupo() throws IOException, UnknownHostException{
        this.setMs(new MulticastSocket(this.getPUERTO()));
        this.setGrupo(this.validarDireccionDelGrupo());
        this.getMs().joinGroup(grupo);
        System.out.println("Se ha logrado unir al grupo con la ip : "+this.getHOST_GRUPO()+" con el puerto: "+this.getPUERTO());
    }
    
    /**
     * Función para poder resolver la dirección ip del grupo que se establecio y obtener la ip local
     * @return Un objeto InetAddres que contiene la dirección ip del grupo
     * @throws UnknownHostException Lanza esta excepción cuando no pueda encontrar la ip del grupo
     */
    private InetAddress validarDireccionDelGrupo() throws UnknownHostException{
        this.setHOST_LOCAL(InetAddress.getLocalHost().getHostAddress());
        return InetAddress.getByName(this.getHOST_GRUPO());
    }
    
    /**
     * Funcion que envia un mensaje a todos los usuarios que esten unidos al grupo
     * @param mensaje
     * @throws IOException
     */
    public void enviarMensaje(Mensaje mensaje) throws IOException{
        byte[] mensajeBytes = Utileria.serailizarObjeto(mensaje);
        this.enviarTamanioMensaje(mensajeBytes.length, this.getGrupo());
        DatagramPacket paquete = new DatagramPacket(mensajeBytes,mensajeBytes.length,this.getGrupo(),this.getPUERTO());
        this.getMs().send(paquete);
    }
    
    /**
     * Función para enviar el tamaño de un mensaje hacia una ip cualquiera
     * @param tamanio tamanio representa el tamaño del mensaje a enviar
     * @param ip ip es la dirección a quien se va a enviar
     * @throws IOException 
     */
    private void enviarTamanioMensaje(Integer tamanio, InetAddress ip) throws IOException{
        byte[] tamanioBytes = Utileria.serailizarObjeto(tamanio);
        DatagramPacket paquete = new DatagramPacket(tamanioBytes,tamanioBytes.length,ip,this.getPUERTO());
        this.getMs().send(paquete);
    }
    
    /**
     * Funcion que recibir un mensaje
     * @return Regresa el mensjae que se le fue enviado a este cliente
     * @throws IOException
     * @throws java.lang.ClassNotFoundException
     */
    public Mensaje recibirMensaje() throws IOException, ClassNotFoundException{
        int tamanio = this.recibirTamanioMensaje();
        byte[] mensajeBytes = new byte[tamanio];
        DatagramPacket paquete = new DatagramPacket(mensajeBytes,tamanio);
        this.getMs().receive(paquete);
        return (Mensaje)Utileria.deseralizarObjeto(paquete.getData());
    }
    
    /**
     * Función para recibir el tamaño de un mensaje
     * @return Regresa el tamaño del mensaje que esta por recibirse
     * @throws IOException 
     * @throws java.lang.ClassNotFoundException 
     */
    private int recibirTamanioMensaje() throws IOException, ClassNotFoundException{
        byte[] tamanioBytes = new byte[this.TAM_MNSJ];
        DatagramPacket paquete = new DatagramPacket(tamanioBytes,tamanioBytes.length);
        this.getMs().receive(paquete);
        return (int)Utileria.deseralizarObjeto(paquete.getData());
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

    /**
     * @return the grupo
     */
    public InetAddress getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(InetAddress grupo) {
        this.grupo = grupo;
    }
    
    //</editor-fold>
    
    public class EscucharMensajes implements Runnable{
        
        MulticastSocket ms;
        
        public EscucharMensajes (MulticastSocket ms){
            this.ms = ms;
        }

        @Override
        public void run(){
            
        }
        
    }
}
