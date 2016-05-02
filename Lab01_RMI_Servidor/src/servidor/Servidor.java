/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.ImplementacionFinanzas;
import rmi.ImplementacionLogin;
import rmi.ImplementacionRRHH;
import rmi.ServidorRMI;

/**
 *
 * @author alejandro
 */
public class Servidor {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    // ***
    // propiedades privadas
    private static String CPREFIX = "Lab01_RMI_Servidor.servidor.Servidor";
    private static Logger logger;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="propiedades publicas">
    // ***
    // propiedades publicas
    public static ServidorRMI servidor;
    public static int puerto = 2014;
    public static ImplementacionLogin loginLocal;
    public static ImplementacionRRHH rrhhLocal;
    public static ImplementacionFinanzas finanzasLocal;
    public static String loginRefRemoto = "LoginRefRemoto";
    public static String rrhhRefRemoto = "RrhhRefRemoto";
    public static String finanzasRefRemoto =  "FinanzasRefRemoto";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    // ***
    // metodos publicos
    public static void main(String[] args) {
        String MPREFIX = " [main(String[] args)]";
        // Iniciamos el logger
        logger = Logger.getLogger("Servidor");

        //Se inicializa el objeto, el cual podrá ser llamado remotamente
        try {
            loginLocal = new ImplementacionLogin();
            rrhhLocal = new ImplementacionRRHH();
            finanzasLocal = new ImplementacionFinanzas();
        } 
        catch(RemoteException re){
            //En caso de haber un error, es mostrado por un mensaje
            logger.log(Level.SEVERE, CPREFIX + MPREFIX + "-> Ha ocurrido un error. Detalle: " + re.getMessage());
        }

        //El objeto se dejerá disponible para una conexión remota
        logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Se va a conectar...");

        servidor = new ServidorRMI();
        if ((servidor.IniciarConexion(loginLocal, loginRefRemoto, puerto)) && 
            (servidor.IniciarConexion(rrhhLocal, rrhhRefRemoto, puerto)) &&
            (servidor.IniciarConexion(finanzasLocal, finanzasRefRemoto, puerto))) { 
            
            //Resultado de la conexión
            logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Se ha establecido la conexión correctamente");
        } 
        else {
            logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Ha ocurrido un error al conectarse");
        }

        System.out.println("Presione cualquier tecla y luego Enter para desconectar el servidor...");
        Scanner lector = new Scanner(System.in);
        lector.next();

        //En caso que presione una tecla el administrador, se detiene el servicio
        try {
            servidor.DetenerConexion(loginRefRemoto);
            servidor.DetenerConexion(rrhhRefRemoto);
            servidor.DetenerConexion(finanzasRefRemoto);
        } 
        catch (RemoteException re) {
            //En caso de haber un error, es mostrado por un mensaje
            logger.log(Level.SEVERE, CPREFIX + MPREFIX + "-> Error al intentar detener la conexión. Detalle: " + re.getMessage());
        }

        System.exit(0);
    }
    //</editor-fold>
}
