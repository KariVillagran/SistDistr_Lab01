/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import dataaccess.LoginDA;
import dto.UsuarioDTO;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi_interface.ILoginRMI;

/**
 *
 * @author alejandro
 */
public class ImplementacionLogin implements ILoginRMI {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    // ***
    // propiedades privadas
    private static String CPREFIX = "Lab01_RMI_Servidor.rmi.ImplementacionLogin";
    private static Logger logger;
    private LoginDA loginDA;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="constructores">
    // ***
    // constructores
    public ImplementacionLogin(){
        logger = Logger.getLogger(getClass().getName());
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    // ***
    // metodos publicos
    @Override
    public UsuarioDTO ValidarUsuario(UsuarioDTO p_Usuario) throws RemoteException {
        String MPREFIX = " [ValidarUsuario(UsuarioDTO p_Usuario)]";
    
        UsuarioDTO objResult = null;
        
        try{
            loginDA = new LoginDA();
            objResult = loginDA.ValidarUsuario(p_Usuario);
        }
        catch(Exception ex){
            logger.log(Level.SEVERE, CPREFIX + MPREFIX + "-> Error al intentar validar el usuario. Detalle: " + ex.getMessage());
            throw new RemoteException(ex.getMessage(), ex);
        }
        
        return objResult;
    }
    // </editor-fold>
}
