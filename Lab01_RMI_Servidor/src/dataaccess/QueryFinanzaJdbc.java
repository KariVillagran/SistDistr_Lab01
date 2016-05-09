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

/**
 *
 * @author alejandro
 */
public class QueryFinanzaJdbc implements IFinanzaRMI{
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    // ***
    // propiedades privadas
    private static String CPREFIX = "Lab01_RMI_Servidor.dataacess.LoginDA";
    private static Logger logger;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="constructores">
    // ***
    // constructores
    public QueryFinanzaJdbc(){
        logger = Logger.getLogger(getClass().getName());
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos privados">
    private FinanzaDTO failUser(){ 
        FinanzaDTO salida=new FinanzaDTO();
        salida.SetId(0);
        return salida;
    }     
    // </editor-fold>
  
    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
     
    // <editor-fold defaultstate="collapsed" desc="BorrarRRHH">
    public Integer borrarRRHH(FinanzaDTO p_Obj) throws Exception{
        String MPREFIX = " [ValidarUsuario(UsuarioDTO p_Obj)]";
        
        FinanzaDTO objResult = null;
        final Connection  conn = ConexionPostgresql.getInstanceBD().IniciarConexion();        
        ArrayList<FinanzaDTO> listaRecurso=new ArrayList<>();
        Integer salidaBorrar=0;
        
        try{
            logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Inicio ejecución");
            Statement stmt = conn.createStatement();
            String sql="DELETE FROM finanza WHERE id="+p_Obj.GetId()+";";
            int rs = stmt.executeUpdate(sql);
            System.out.println("dataaccess.LoginDA.ValidarUsuario sql="+sql);
            salidaBorrar=rs;
            stmt.close();
            logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Ejecución finalizada correctamente");
        }
        catch(SQLException ex){
            logger.log(Level.SEVERE, CPREFIX + MPREFIX + "-> Error al intentar Validar el Usuario. Detalle: " + ex.getMessage());
            throw ex;
        }
        finally{
            if(conn != null){
                conn.close();
            }
        }
        
        return salidaBorrar;
    }
    // </editor-fold>                 
      
    // <editor-fold defaultstate="collapsed" desc="GetFinanzaById">
    @Override
    public FinanzaDTO GetFinanzaById(int p_Id) throws Exception {
        String MPREFIX = " [ValidarUsuario(UsuarioDTO p_Obj)]";
        
        try{    
            ArrayList<FinanzaDTO> lista=new ArrayList<>();
            FinanzaDTO objResult = null;
            final Connection  conn = ConexionPostgresql.getInstanceBD().IniciarConexion();
            
            try{
                logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Inicio ejecución");
                
                Statement stmt = conn.createStatement();
                String sql="SELECT id, nombrepersona, fchmovimiento, saldomovimiento FROM finanza where id="+p_Id+";";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("dataaccess.LoginDA.ValidarUsuario sql="+sql);
                
                while (rs.next())
                {
                    objResult = new FinanzaDTO();
                    objResult.SetId(rs.getInt("id"));
                    objResult.SetNombrePersonal(rs.getString("nombrepersona"));
                    objResult.SetFchMovimiento(rs.getDate("fchmovimiento"));
                    objResult.SetSaldoMovimiento((double)rs.getInt("saldomovimiento"));
                    lista.add(objResult);
                    break;
                }
                rs.close();
                stmt.close();
                logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Ejecución finalizada correctamente");
            }
            catch(SQLException ex){
                objResult=failUser();
                logger.log(Level.SEVERE, CPREFIX + MPREFIX + "-> Error al intentar Validar el Usuario. Detalle: " + ex.getMessage());
                
            }
            finally{
                if(conn != null){
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(QueryFinanzaJdbc.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            return objResult;
        }
        catch(ClassNotFoundException ex){
           // Logger.getLogger(QueryFinanzaJdbc.class.getName()).log(Level.SEVERE, null, ex);
         
        } catch (SQLException ex) {
           // Logger.getLogger(QueryFinanzaJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GetListaFinanzasAll">
    @Override 
    public List<FinanzaDTO> GetListaFinanzasAll() throws Exception {
        String MPREFIX = " [ValidarUsuario(UsuarioDTO p_Obj)]";
        
        ArrayList<FinanzaDTO> lista=new ArrayList<>();
        FinanzaDTO objResult = null;
        final Connection  conn = ConexionPostgresql.getInstanceBD().IniciarConexion();        
        
        try{
            Statement stmt = conn.createStatement();
            String sql="SELECT id, nombrepersona, fchmovimiento, saldomovimiento FROM finanza;";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("dataaccess.LoginDA.ValidarUsuario sql="+sql);
            
            while (rs.next()) 
            {
               objResult = new FinanzaDTO();
               objResult.SetId(rs.getInt("id"));
               objResult.SetNombrePersonal(rs.getString("nombrepersona"));
               objResult.SetFchMovimiento(rs.getDate("fchmovimiento"));
               objResult.SetSaldoMovimiento((double)rs.getInt("saldomovimiento"));
               lista.add(objResult);
               
            }   
            rs.close();
            stmt.close();   
        }
        catch(SQLException ex){
            objResult=failUser();
            throw ex;
        }
        finally{
            if(conn != null){
                conn.close();
            }
        }
        
        return lista;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="InsertarFinanza">
    @Override
    public boolean InsertarFinanza(FinanzaDTO p_Obj) throws Exception {
        String MPREFIX = " [ValidarUsuario(UsuarioDTO p_Obj)]";
        
        Integer salida=0;
        FinanzaDTO objResult = null;
        final Connection  conn = ConexionPostgresql.getInstanceBD().IniciarConexion();        
        
        try{
            logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Inicio ejecución");
        
            // Obtenemos el objeto "Connection" e iniciamos la conexión
            Statement stmt = conn.createStatement();
            String sql="INSERT INTO public.finanza(id, nombrepersona, fchmovimiento, saldomovimiento) "+
            "VALUES (nextval('finanzas'),'"+p_Obj.GetNombrePersonal()+"','"+p_Obj.GetFchMovimiento()+"',"+p_Obj.GetSaldoMovimiento()+");";
            
            int rs = stmt.executeUpdate(sql);
            salida=rs;
            System.out.println("dataaccess.LoginDA.ValidarUsuario sql="+sql);
            stmt.close();
         
            logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Ejecución finalizada correctamente");
      
  
        } catch (Exception ex) {
            salida=0;
            Logger.getLogger(QueryRRHHJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryRRHHJdbc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if(salida>0)
        {return true;}
        else{return false;}
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="UpdateFinanza">
    @Override
    public boolean UpdateFinanza(FinanzaDTO p_Obj) throws Exception {
        Integer salida=0;
        final Connection  conn = ConexionPostgresql.getInstanceBD().IniciarConexion();        
        Statement stmt = conn.createStatement();    
        String sql="UPDATE finanza SET  nombrepersona='"+p_Obj.GetNombrePersonal()+"',"
            + " fchmovimiento='"+p_Obj.GetFchMovimiento()+"',"
            + " saldomovimiento='"+p_Obj.GetSaldoMovimiento()+"'"
            + " WHERE id="+p_Obj.GetId()+";";
        
        System.out.println("dataaccess.LoginDA.ValidarUsuario sql="+sql);

        int rs = stmt.executeUpdate(sql);
            salida=rs;
            stmt.close();
            if(salida>0)
            {return true;}
            else
            {return false;}    
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="DeleteFinanza">
    @Override
    public boolean deleteFinanza(FinanzaDTO p_Obj) throws Exception {   
        Integer salida=0;
        final Connection  conn = ConexionPostgresql.getInstanceBD().IniciarConexion();        
        Statement stmt = conn.createStatement();    
        String sql="delete from  finanza where id="+p_Obj.GetId()+";";
        System.out.println("dataaccess.LoginDA.ValidarUsuario sql="+sql);

    
        int rs = stmt.executeUpdate(sql);
            salida=rs;
            stmt.close();
            if(salida>0)
            {return true;}
            else
            {return false;}   
    }
    // </editor-fold>
    
    // </editor-fold>
}
