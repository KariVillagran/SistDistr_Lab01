/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import conf.Parameters;
import dto.UsuarioDTO;
import rmi.ConexionCliente;
import rmi_interface.ILoginRMI;
import rmi_interface.IUsuarioRMI;

/**
 *
 * @author aialiagam
 */
public class LoginModel {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    private ConexionCliente conexionRMI;
    private static LoginModel instance = null;
    private IUsuarioRMI usuarioRMI;
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
    public UsuarioDTO ValidarUsuario(UsuarioDTO p_Obj) throws Exception{
        UsuarioDTO objResult = null;
        
        try{
            // Iniciamos el registro del RMI
            //if(this.conexionRMI.iniciarRegistro(Parameters.PARAM_IP_SERVER_RMI, Parameters.PARAM_PORT_SERVER_RMI)){
            //    this.usuarioRMI = this.conexionRMI.getServidorLogin();
            //}
            
            objResult = new UsuarioDTO();
            
            objResult.SetUserName(p_Obj.GetUserName());
            objResult.SetPassword(p_Obj.GetPassword());
            objResult.SetNombreCompleto("Usuario Prueba");
            objResult.SetRol("Recursos Humanos");
            objResult.SetId(1);
        }
        catch(Exception ex){
            throw ex;
        }
        
        return objResult;
    }
    // </editor-fold>
}
