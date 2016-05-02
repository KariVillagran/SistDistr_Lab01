/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Logger;
import java.util.logging.Level;
import rmi_interface.IFinanzaRMI;
import rmi_interface.IRecursoHumanoRMI;
import rmi_interface.IUsuarioRMI;

/**
 *
 * @author alejandro
 */
public class ServidorRMI {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    // ***
    //propiedades privadas
    private static String CPREFIX = "Lab01_RMI_Servidor.rmi.ServidorRMI";
    private Registry registro;
    private boolean conectado;
    private static Logger logger;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="constructores">
    // ***
    //constructores
    public ServidorRMI(){
        String MPREFIX = " [ServidorRMI()]";
        
        this.conectado = false;
        this.registro = null;
        logger = Logger.getLogger(getClass().getName());
        logger.log(Level.INFO, String.format("{0} {1} {2}", CPREFIX, MPREFIX, " -> Se ha instanciado la Clase"));
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos privados">
    // ***
    //metodos privados
    private void IniciarRegistro(int puerto) throws RemoteException{
        String MPREFIX = " [IniciarRegistro(int puerto)]";
        
        try{
            registro = LocateRegistry.getRegistry(puerto);
            registro.list();
            
            conectado = true;
            
            logger.log(Level.INFO, String.format("{0} {1} {2}", CPREFIX,  MPREFIX, "-> Se ha iniciado el registro correctamente"));
        }
        catch(RemoteException ex){
            conectado = false;
            throw  ex;
        }
    } 
    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="metodos publicos"> 
    // ***
    //metodos publicos
    public boolean IniciarConexion(Object object, String nombre, int puerto){
        String MPREFIX = " [IniciarConexion(Object object, String nombre, int puerto)]";
        
        boolean objResult = false;
        
        try{
            //instanciamos el registro
            this.registro = GetRegistro(puerto);
            
            if(object.getClass().equals(ImplementacionFinanzas.class)){
                // Guardamos un registro en el log
                 logger.log(Level.INFO, String.format("{0} {1} {2}", CPREFIX, MPREFIX, "-> Se agregará " + nombre + " con el objecto Usuario"));
                //De tal manera que aquí se castee la interface que le corresponde
                registro.rebind(nombre, (IFinanzaRMI)object);
            }
            else if (object.getClass().equals(ImplementacionRRHH.class)){
                // Guardamos un registro en el log
                 logger.log(Level.INFO, String.format("{0} {1} {2}", CPREFIX, MPREFIX, "-> Se agregará " + nombre + " con el objecto Usuario"));
                //De tal manera que aquí se castee la interface que le corresponde
                registro.rebind(nombre, (IRecursoHumanoRMI)object);
            }
            else if (object.getClass().equals(ImplementacionLogin.class)){
                // Guardamos un registro en el log
                 logger.log(Level.INFO, String.format("{0} {1} {2}", CPREFIX, MPREFIX, "-> Se agregará " + nombre + " con el objecto Usuario"));
                //De tal manera que aquí se castee la interface que le corresponde
                registro.rebind(nombre, (IUsuarioRMI)object);
            }
            
            //Si todo lo anterior funciona, la conexión se realizó correctamente
            objResult = true;
        }
        catch(RemoteException ex){
            logger.log(Level.SEVERE, String.format("{0} {1} {2}", CPREFIX, MPREFIX, 
                    "-> Error al intentar iniciar la conexión. Detalle: " + ex.getMessage()), ex);
        }
        
        return objResult;
    }
    
    // Quita del registro del servidor la referencia al objeto remoto
    public void DetenerConexion(String nombreUsado) throws RemoteException {
        String MPREFIX = " [DetenerConexion(String nombreUsado)]";
        
        try {
            //Se saca de RMI Registry el objeto "Ejemplo-RMI"
            //El cual ya no estará disponible para ser llamado remotamente
            registro.unbind(nombreUsado);
            conectado = false;
        } 
        catch (NotBoundException | AccessException ex) {
            //En caso de haber un error, es mostrado por un mensaje
            logger.log(Level.SEVERE, String.format("{0} {1} {2}", CPREFIX, MPREFIX, 
                    "-> Error al intentar detener la conexión!"), ex);
            conectado = true;
        }
    }
    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="metodos accesores"> 
    // ***
    // metodos accesores
    public Registry GetRegistro(int puerto) throws RemoteException{
        if(this.registro == null){
            this.IniciarRegistro(puerto);
        }
        
        return this.registro;
    }
    
    public void SetRegistro(Registry registro){
        this.registro = registro;
    }
    
    public boolean GetConectado(){
        return this.conectado;
    }
    
    public void SetConectado(boolean conectado){
        this.conectado = conectado;
    }
    // </editor-fold>
}
