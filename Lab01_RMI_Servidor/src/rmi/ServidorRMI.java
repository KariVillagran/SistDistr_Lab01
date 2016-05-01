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
        logger.log(Level.INFO, CPREFIX +  MPREFIX + " -> Se ha instanciado la Clase");
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
            
            logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Se ha iniciado el registro correctamente");
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
                
            }
            else if (object.getClass().equals(ImplementacionRRHH.class)){
                
            }
            
            //Si todo lo anterior funciona, la conexión se realizó correctamente
            objResult = true;
        }
        catch(RemoteException ex){
            logger.log(Level.SEVERE, CPREFIX + MPREFIX + "-> Error al intentar iniciar la conexión. Detalle: " + ex.getMessage());
        }
        
        return objResult;
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
