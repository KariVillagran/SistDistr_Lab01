package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi_interface.IFinanzaRMI;
import rmi_interface.IRecursoHumanoRMI;
import rmi_interface.ILoginRMI;
/**
 *
 * @author Daniel Wladdimiro Cottet
 * @title Taller de sistemas distribuidos - Clase 1
 */

public class ConexionCliente {

    // <editor-fold defaultstate="collapsed" desc="propiedades privadas y publicas">
    
    private Registry registry;  //Registro de la conexión del usuario con el servidor
    private boolean conectado;  //Estado de conexión del usuario con el servidor
    private IFinanzaRMI servidorFinanza; //Interface necesaria para la comunición con el objecto del servidor
    private IRecursoHumanoRMI servidorRRHH; //Interface necesaria para la comunición con el objecto del servidor
    private ILoginRMI servidorLogin; //Interface necesaria para la comunición con el objecto del servidor
    public static String loginRefRemoto = "LoginRefRemoto";
    public static String rrhhRefRemoto = "RrhhRefRemoto";
    public static String finanzasRefRemoto =  "FinanzasRefRemoto";
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="constructores">
    
    public ConexionCliente() {
        this.conectado = false;
        this.registry = null;
        this.servidorFinanza = null;
        this.servidorLogin = null;
        this.servidorRRHH = null;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    
    public boolean iniciarRegistro(String IP, int Puerto) throws RemoteException {
        try {
            //Se le otorga el permiso necesario para poder ejecutar las funciones correspondientes
            java.security.AllPermission allPermision = new java.security.AllPermission();          
            System.setProperty("java.security.policy", "rmi.policy");

            //Se inicia RMI-Registry para el registro del objeto
            try {
                //Obtenemos el Registry del servidor RMI
                registry = LocateRegistry.getRegistry(IP, Puerto);

            //De existir algún error con el registro que se desea obtener del servidor, se mostrará un mensaje
            } catch (RemoteException e) {
                System.out.println(IP + ":" + Puerto);
                System.out.println(e.getMessage());
                System.out.println(e.toString());
            }
            
            //Vamos al Registry y miramos el Objeto "nombreObjRemoto" para poder usarlo.
            servidorFinanza = (IFinanzaRMI) registry.lookup(finanzasRefRemoto);
            servidorLogin = (ILoginRMI) registry.lookup(loginRefRemoto);
            servidorRRHH = (IRecursoHumanoRMI) registry.lookup(rrhhRefRemoto);

            this.conectado = true;
            return true;
            
        //De existir algún error con la conexión al servidor, se mostrará un mensaje
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error: No se posee conexión");
            
            this.conectado = false;
            return false;
        }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Metodos Accesores y Mutadores">
    //Getting y Setting de los atributos de ConexionCliente

    public Registry getRegistry() {
        return registry;
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public boolean getConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    /**
     * @return the servidorFinanza
     */
    public IFinanzaRMI getServidorFinanza() {
        return servidorFinanza;
    }

    /**
     * @param servidorFinanza the servidorFinanza to set
     */
    public void setServidorFinanza(IFinanzaRMI servidorFinanza) {
        this.servidorFinanza = servidorFinanza;
    }

    /**
     * @return the servidorRRHH
     */
    public IRecursoHumanoRMI getServidorRRHH() {
        return servidorRRHH;
    }

    /**
     * @param servidorRRHH the servidorRRHH to set
     */
    public void setServidorRRHH(IRecursoHumanoRMI servidorRRHH) {
        this.servidorRRHH = servidorRRHH;
    }

    /**
     * @return the servidorLogin
     */
    public ILoginRMI getServidorLogin() {
        return servidorLogin;
    }

    /**
     * @param servidorLogin the servidorLogin to set
     */
    public void setServidorLogin(ILoginRMI servidorLogin) {
        this.servidorLogin = servidorLogin;
    }
    
    // </editor-fold>
}
