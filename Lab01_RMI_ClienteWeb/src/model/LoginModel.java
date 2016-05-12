/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import conf.Parameters;
import dto.UsuarioDTO;
import java.rmi.RemoteException;
import rmi.ConexionCliente;
import rmi_interface.ILoginRMI;

/**
 *
 * @author aialiagam
 */
public class LoginModel {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    private ConexionCliente conexionRMI;
    private static LoginModel instance = null;
    private ILoginRMI loginRMI;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="constructores">
    
    // constructor privado
    private LoginModel(){
        this.conexionRMI = new ConexionCliente();
    }
    
    // metodo para obtener la instancia del singleton
    public static LoginModel GetInstance(){
        if(LoginModel.instance == null){
            LoginModel.instance = new LoginModel();
        }
    
        return LoginModel.instance;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    public UsuarioDTO ValidarUsuario(UsuarioDTO p_Obj) throws Exception, RemoteException{
        UsuarioDTO objResult = null;
        
        try{
            // Iniciamos el registro del RMI
            if(this.conexionRMI.iniciarRegistro(Parameters.PARAM_IP_SERVER_RMI, Parameters.PARAM_PORT_SERVER_RMI)){
                this.loginRMI = this.conexionRMI.getServidorLogin();
            }
            else{
                throw new RemoteException("No se ha podido iniciar la conexión con el servidor-rmi!");
            }
            
            objResult = this.loginRMI.ValidarUsuario(p_Obj);
        }
        catch(RemoteException ex){
            throw ex;
        }
        catch(Exception ex){
            throw ex;
        }
        
        return objResult;
    }
    // </editor-fold>
}
