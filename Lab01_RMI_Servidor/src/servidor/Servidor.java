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
 * @author Daniel Wladdimiro Cottet
 * @title Taller de sistemas distribuidos - Clase 1
 */

public class Servidor {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas y publicas">
    public static ServidorRMI servidor;
    public static int puerto = conf.Parameters.PARAM_SERVER_RMI_PORT;
    public static ImplementacionLogin loginLocal;
    public static ImplementacionRRHH rrhhLocal;
    public static ImplementacionFinanzas finanzasLocal;
    public static String loginRefRemoto = "LoginRefRemoto";
    public static String rrhhRefRemoto = "RrhhRefRemoto";
    public static String finanzasRefRemoto =  "FinanzasRefRemoto";
    public static String nombreReferenciaRemota = "Ejemplo-Síncrono-RMI";
    private static Logger logger;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodo main">
    public static void main(String[] args) {
        logger = Logger.getLogger("Servidor");
        
        //Se inicializa el objeto, el cual podrá ser llamado remotamente
        try {
            loginLocal = new ImplementacionLogin();
            rrhhLocal = new ImplementacionRRHH();
            finanzasLocal = new ImplementacionFinanzas();
        } catch (RemoteException re) {
            //En caso de haber un error, es mostrado por un mensaje
            logger.log(Level.SEVERE, re.getMessage());
        }

        //El objeto se dejerá disponible para una conexión remota
        logger.log(Level.INFO, "Se va a conectar...");
        //Instanciamos el Servidor-RMI
        servidor = new ServidorRMI();

        boolean rslConLogin = servidor.IniciarConexion(loginLocal, loginRefRemoto, puerto);
        boolean rslConRRHH = servidor.IniciarConexion(rrhhLocal, rrhhRefRemoto, puerto);
        boolean rslConFinanzas = servidor.IniciarConexion(finanzasLocal, finanzasRefRemoto, puerto);
        
        if (rslConLogin && rslConRRHH && rslConFinanzas ) {
            logger.log(Level.INFO, "Se ha establecido la conexión correctamente");
        } else {
            logger.log(Level.INFO, "Ha ocurrido un error al conectarse");
        }

        System.out.println("Presione cualquier tecla y luego Enter para desconectar el servidor...");
        Scanner lector = new Scanner(System.in);
        lector.next();

        //En caso que presione una tecla el administrador, se detiene el servicio
        try {
            servidor.detenerConexion(loginRefRemoto);
            servidor.detenerConexion(rrhhRefRemoto);
            servidor.detenerConexion(finanzasRefRemoto);
        } catch (RemoteException re) {
            //En caso de haber un error, es mostrado por un mensaje
            logger.log(Level.SEVERE, re.getMessage());
        }

        System.exit(0);
    }
    // </editor-fold>
}
