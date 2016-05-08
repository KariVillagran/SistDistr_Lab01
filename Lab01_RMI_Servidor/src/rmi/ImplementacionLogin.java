/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import dataaccess.LoginDA;
import dataaccess.QueryUsuarioJdbc;
import dto.UsuarioDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi_interface.ILoginRMI;

/**
 *
 * @author alejandro
 */
public class ImplementacionLogin extends UnicastRemoteObject implements ILoginRMI {
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
    public ImplementacionLogin() throws RemoteException {
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
        QueryUsuarioJdbc queryUsuarioJdbc=new QueryUsuarioJdbc();
        try{
            loginDA = new LoginDA();
            objResult = queryUsuarioJdbc.validarUsuario(p_Usuario);
        }
        catch(Exception ex){
            throw new RemoteException(ex.getMessage(), ex);
        }
        
        return objResult;
    }
    // </editor-fold>
}
