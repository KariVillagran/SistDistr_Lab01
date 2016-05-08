package servidor;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.Implementacion;
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

    public static ServidorRMI servidor;
    public static int puerto = 2014;
    public static Implementacion objetoLocal;
    public static ImplementacionLogin loginLocal;
    public static ImplementacionRRHH rrhhLocal;
    public static ImplementacionFinanzas finanzasLocal;
    public static String loginRefRemoto = "LoginRefRemoto";
    public static String rrhhRefRemoto = "RrhhRefRemoto";
    public static String finanzasRefRemoto =  "FinanzasRefRemoto";
    public static String nombreReferenciaRemota = "Ejemplo-Síncrono-RMI";
    static Logger logger;

    public static void main(String[] args) {
        logger = Logger.getLogger("Servidor");

        //Se inicializa el objeto, el cual podrá ser llamado remotamente
        try {
            objetoLocal = new Implementacion();
            loginLocal = new ImplementacionLogin();
            rrhhLocal = new ImplementacionRRHH();
            finanzasLocal = new ImplementacionFinanzas();
        } catch (RemoteException re) {
            //En caso de haber un error, es mostrado por un mensaje
            logger.log(Level.SEVERE, re.getMessage());
        }

        //El objeto se dejerá disponible para una conexión remota
        logger.log(Level.INFO, "Se va a conectar...");

        servidor = new ServidorRMI();

        boolean resultadoConexion = servidor.iniciarConexion(objetoLocal, nombreReferenciaRemota, puerto);
        boolean resultadoConexion2 = servidor.IniciarConexion(loginLocal, loginRefRemoto, puerto);
        boolean resultadoConexion3= servidor.IniciarConexion(rrhhLocal, rrhhRefRemoto, puerto);
        boolean resultadoConexion4= servidor.IniciarConexion(finanzasLocal, finanzasRefRemoto, puerto);
        
        
        if (resultadoConexion && resultadoConexion2 && resultadoConexion3 && resultadoConexion4 ) {
            logger.log(Level.INFO, "Se ha establecido la conexión correctamente");
        } else {
            logger.log(Level.INFO, "Ha ocurrido un error al conectarse");
        }

        System.out.println("Presione cualquier tecla y luego Enter para desconectar el servidor...");
        Scanner lector = new Scanner(System.in);
        lector.next();

        //En caso que presione una tecla el administrador, se detiene el servicio
        try {
  
            servidor.detenerConexion(nombreReferenciaRemota);
            servidor.detenerConexion(loginRefRemoto);
            servidor.detenerConexion(rrhhRefRemoto);
            servidor.detenerConexion(finanzasRefRemoto);
            
      
        } catch (RemoteException re) {
            //En caso de haber un error, es mostrado por un mensaje
            logger.log(Level.SEVERE, re.getMessage());
        }

        System.exit(0);
    }
}
