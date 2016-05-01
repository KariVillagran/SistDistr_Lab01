/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import dto.UsuarioDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alejandro
 */
public class LoginDA {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    // ***
    // propiedades privadas
    private static String CPREFIX = "Lab01_RMI_Servidor.dataacess.LoginDA";
    private static Logger logger;
    private ConexionPostgresql conexion;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="constructores">
    // ***
    // constructores
    public LoginDA(){
        logger = Logger.getLogger(getClass().getName());
        this.conexion = null;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    // *** 
    // metodos publicos
    public UsuarioDTO ValidarUsuario(UsuarioDTO p_Obj) throws Exception{
        String MPREFIX = " [ValidarUsuario(UsuarioDTO p_Obj)]";
        
        UsuarioDTO objResult = null;
        conexion = new ConexionPostgresql();
        Connection conn = conexion.IniciarConexion();
        
        try{
            logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Inicio ejecución");
            
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery( "select id, " +
                    "username, " +
                    "nombrecompleto, " +
                    "password, " + 
                    "rol " +
                    "from login " +
                    "where username='" + p_Obj.GetUserName() + "' and " +
                          "password='" + p_Obj.GetPassword() + "';");
            
            while (rs.next()) {
               objResult = new UsuarioDTO();
               objResult.SetId(rs.getInt("id"));
               objResult.SetUserName(rs.getString("username"));
               objResult.SetNombreCompleto(rs.getString("nombrecompleto"));
               objResult.SetPassword(rs.getString("password"));
               objResult.SetRol(rs.getString("rol"));
               
               break;
            }
            
            rs.close();
            stmt.close();
            conn.close();
         
            logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Ejecución finalizada correctamente");
        }
        catch(Exception ex){
            logger.log(Level.SEVERE, CPREFIX + MPREFIX + "-> Error al intentar Validar el Usuario. Detalle: " + ex.getMessage());
            conn.close();
            
            throw ex;
        }
        
        return objResult;
    }
    // </editor-fold>
}
