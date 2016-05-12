package rmi;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi_interface.IFinanzaRMI;
import rmi_interface.ILoginRMI;
import rmi_interface.IRecursoHumanoRMI;

/**
 *
 * @author Daniel Wladdimiro Cottet
 * @title Taller de sistemas distribuidos - Clase 1
 */

public class ServidorRMI {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    private Registry registro;
    private boolean conectado;
    private static String CPREFIX = "Lab01_RMI_Servidor.rmi.ServidorRMI";
    private static Logger logger;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="constructores">
    public ServidorRMI() {
        String MPREFIX = " [ServidorRMI()]";
        this.conectado = false;
        this.registro = null;
        logger = Logger.getLogger(getClass().getName());
        logger.log(Level.INFO, "Se ha instanciado la clase Servidor RMI");
        logger.log(Level.INFO, String.format("%s %s %s", CPREFIX, MPREFIX, " -> Se ha instanciado la Clase"));
    }
    // </editor-fold>
   
    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    
    // <editor-fold defaultstate="collapsed" desc="detenerConexion">
    
    //Quita del registro del servidor la referencia al objeto remoto
    public void detenerConexion(String nombreUsado) throws RemoteException {
     String MPREFIX = " [DetenerConexion(String nombreUsado)]";
        
        try {
            //Se saca de RMI Registry el objeto "Ejemplo-RMI"
            //El cual ya no estará disponible para ser llamado remotamente
            registro.unbind(nombreUsado);
            conectado = false;
        } 
        catch (NotBoundException | AccessException ex) {
            //En caso de haber un error, es mostrado por un mensaje
            logger.log(Level.SEVERE, String.format("%s %s %s", CPREFIX, MPREFIX, 
                    "-> Error al intentar detener la conexión!"), ex);
            conectado = true;
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="metodos accesores y mutadores">
    public void setRegistro(Registry registro) {
        this.registro = registro;
    }

    public boolean getConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public Registry GetRegistro(int puerto) throws RemoteException{
        if(this.registro == null){
            this.IniciarRegistro(puerto);
        }
        
        return this.registro;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="IniciarConexion">
    public boolean IniciarConexion(Object object, String nombre, int puerto){
        String MPREFIX = " [IniciarConexion(Object object, String nombre, int puerto)]";
        
        boolean objResult = false;
        
        try{
            //instanciamos el registro
            this.registro = GetRegistro(puerto);
            
            if(object.getClass().equals(ImplementacionFinanzas.class)){
                // Guardamos un registro en el log
                logger.log(Level.INFO, String.format("%s %s %s", CPREFIX, MPREFIX, "-> Se agregará " + nombre + " con el objecto Usuario"));
                //De tal manera que aquí se castee la interface0 que le corresponde
                registro.rebind(nombre, (IFinanzaRMI)object);
            }
            else if (object.getClass().equals(ImplementacionRRHH.class)){
                // Guardamos un registro en el log
                logger.log(Level.INFO, String.format("%s %s %s", CPREFIX, MPREFIX, "-> Se agregará " + nombre + " con el objecto Usuario"));
                //De tal manera que aquí se castee la interface que le corresponde
                registro.rebind(nombre, (IRecursoHumanoRMI)object);
            }
            else if (object.getClass().equals(ImplementacionLogin.class)){
                // Guardamos un registro en el log
                logger.log(Level.INFO, String.format("%s %s %s", CPREFIX, MPREFIX, "-> Se agregará " + nombre + " con el objecto Usuario"));
                //De tal manera que aquí se castee la interface que le corresponde
                registro.rebind(nombre, (ILoginRMI)object);
            }
            
            //Si todo lo anterior funciona, la conexión se realizó correctamente
            objResult = true;
        }
        catch(RemoteException ex){
            logger.log(Level.SEVERE, String.format("%s %s %s", CPREFIX, MPREFIX, 
                    "-> Error al intentar iniciar la conexión. Detalle: " + ex.getMessage()), ex);
        }
        
        return objResult;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="IniciarRegistro">
    private void IniciarRegistro(int puerto) throws RemoteException{
        String MPREFIX = " [IniciarRegistro(int puerto)]";
        
        try{
            registro = LocateRegistry.getRegistry(puerto);
            registro.list();
            
            conectado = true;
            
            logger.log(Level.INFO, String.format("%s %s %s", CPREFIX,  MPREFIX, "-> Se ha iniciado el registro correctamente"));
        }
        catch(RemoteException ex){
            // Volvemos a realizar el proceso del try, ya que al iniciar por primera vez el registro
            // este se cae, pero al volverlo a iniciar funciona sin problemas
            registro = LocateRegistry.createRegistry(puerto); //Se creará el objeto a nivel de localhost
            registro.list();  //Por lo que posteriormente, entrega una registro con la lista de objetos remotos
            conectado = true;
            
            //conectado = false;
            //throw  ex;
        }
    } 
    // </editor-fold>
    
    // </editor-fold>
}
