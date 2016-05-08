/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import dto.FinanzaDTO;
import dto.RecursoHumanoDTO;
import dto.UsuarioDTO;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi_interface.IFinanzaRMI;
import rmi_interface.IUsuarioRMI;

/**
 *
 * @author alejandro
 */
public class QueryUsuarioJdbc implements IUsuarioRMI{
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    // ***
    // propiedades privadas
    private static String CPREFIX = "Lab01_RMI_Servidor.dataacess.LoginDA";
    private static Logger logger;
    // </editor-fold>
    

    public UsuarioDTO validarUsuario(UsuarioDTO p_Obj) throws Exception{
        String MPREFIX = " [ValidarUsuario(UsuarioDTO p_Obj)]";
         final Connection  conn = ConexionPostgresql.getInstanceBD().IniciarConexion();
        UsuarioDTO objResult = null;
       
        
        try{
            //logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Inicio ejecución");

            // Obtenemos el objeto "Connection" e iniciamos la conexión
            Statement stmt = conn.createStatement();
            String sql="select id, " +
                    "username, " +
                    "nombrecompleto, " +
                    "password, " + 
                    "rol " +
                    "from login " +
                    "where username='" + p_Obj.GetUserName() + "' and " +
                          "password='" + p_Obj.GetPassword() + "';";
            System.out.println("dataaccess.QueryUsuarioJdbc.validarUsuario()"+sql);
            ResultSet rs = stmt.executeQuery(sql);
            
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
            //conn.close();
         
          //  logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Ejecución finalizada correctamente");
        }
        catch(SQLException ex){
            objResult=failUser();
           // logger.log(Level.SEVERE, CPREFIX + MPREFIX + "-> Error al intentar Validar el Usuario. Detalle: " + ex.getMessage());
            throw ex;
        }
        finally{
            if(conn != null){
                conn.close();
            }
        }
        
        
        return objResult;
    }
    // </editor-fold>
 

  private UsuarioDTO failUser()
  { UsuarioDTO salida=new UsuarioDTO();
      salida.SetNombreCompleto("Error");
      salida.SetNombreCompleto("error");
      salida.SetId(0);
      return salida;
  }    

    @Override
    public UsuarioDTO validarUsuarioDTO(UsuarioDTO user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
