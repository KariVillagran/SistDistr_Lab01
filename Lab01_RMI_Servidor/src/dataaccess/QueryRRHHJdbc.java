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
import rmi_interface.IRecursoHumanoRMI;

/**
 *
 * @author alejandro
 */
public class QueryRRHHJdbc implements IRecursoHumanoRMI{
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    // ***
    // propiedades privadas
    private static String CPREFIX = "Lab01_RMI_Servidor.dataacess.LoginDA";
    private static Logger logger;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="constructores">
    // ***
    // constructores
    public QueryRRHHJdbc(){
        logger = Logger.getLogger(getClass().getName());
    }
    // </editor-fold>
    

          
 
           
    
  private RecursoHumanoDTO failUser()
  { RecursoHumanoDTO salida=new RecursoHumanoDTO();
      salida.SetId(0);
      return salida;
  }     

    @Override
    public RecursoHumanoDTO GetRecursoHumanoById(int p_Id) throws Exception {
       
          String MPREFIX = " [ValidarUsuario(UsuarioDTO p_Obj)]";
        RecursoHumanoDTO objResult = null;
         final Connection  conn = ConexionPostgresql.IniciarConexion();
        ArrayList<RecursoHumanoDTO> lista=new ArrayList<>();
        try{
            logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Inicio ejecución");
        
            // Obtenemos el objeto "Connection" e iniciamos la conexión
            
            Statement stmt = conn.createStatement();
            String sql="SELECT id,"
                    + " nombrepersona, "
                    + "fchnacimiento, "
                    + "direccion, "
                    + "comuna, "
                    + "region,"
                    + "email, "
                    + "telefono, "
                    + "sexo, "
                    + "fchcontrato, "
                    + "departamento FROM recursohumano where id="+p_Id+";";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.println("dataaccess.LoginDA.ValidarUsuario sql="+sql);
            
            while (rs.next()) {
                
               objResult = new RecursoHumanoDTO();
               objResult.SetId(rs.getInt("id"));
               objResult.SetNombrePersona(rs.getString("nombrepersona"));
               objResult.SetFchNacimiento(rs.getDate("fchnacimiento"));
               objResult.SetDireccion(rs.getString("direccion"));
               objResult.SetComuna(rs.getString("comuna"));
               objResult.SetRegion(rs.getString("region"));
               objResult.SetEmail(rs.getString("email"));
               objResult.SetTelefono(rs.getString("telefono"));
               objResult.SetSexo(rs.getString("sexo"));
               objResult.SetFchContrato(rs.getDate("fchcontrato"));
               objResult.SetDireccion(rs.getString("departamento"));
               lista.add(objResult);
               break;
            }
            
            rs.close();
            stmt.close();
         
            logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Ejecución finalizada correctamente");
        }
        catch(SQLException ex){
            logger.log(Level.SEVERE, CPREFIX + MPREFIX + "-> Error al intentar Validar el Usuario. Detalle: " + ex.getMessage());
        }
        finally{
            if(conn != null){
                conn.close();
            }
        }
        return objResult;
        
    }

    @Override
    public List<RecursoHumanoDTO> GetListRecursoHumanoAll() throws Exception {
    String MPREFIX = " [ValidarUsuario(UsuarioDTO p_Obj)]";
        RecursoHumanoDTO objResult = null;
         final Connection  conn = ConexionPostgresql.IniciarConexion();
        ArrayList<RecursoHumanoDTO> lista=new ArrayList<>();
        try{
            logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Inicio ejecución");
        
            // Obtenemos el objeto "Connection" e iniciamos la conexión
            Statement stmt = conn.createStatement();
            String sql="SELECT id,"
                    + " nombrepersona, "
                    + "fchnacimiento, "
                    + "direccion, "
                    + "comuna, "
                    + "region,"
                    + "email, "
                    + "telefono, "
                    + "sexo, "
                    + "fchcontrato, "
                    + "departamento FROM recursohumano ;";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.println("dataaccess.LoginDA.ValidarUsuario sql="+sql);
            
            while (rs.next()) {
                
               objResult = new RecursoHumanoDTO();
               objResult.SetId(rs.getInt("id"));
               objResult.SetNombrePersona(rs.getString("nombrepersona"));
               objResult.SetFchNacimiento(rs.getDate("fchnacimiento"));
               objResult.SetDireccion(rs.getString("direccion"));
               objResult.SetComuna(rs.getString("comuna"));
               objResult.SetRegion(rs.getString("region"));
               objResult.SetEmail(rs.getString("email"));
               objResult.SetTelefono(rs.getString("telefono"));
               objResult.SetSexo(rs.getString("sexo"));
               objResult.SetFchContrato(rs.getDate("fchcontrato"));
               objResult.SetDireccion(rs.getString("departamento"));
               lista.add(objResult);
            }
            
            rs.close();
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
        return lista;
    }

    @Override
    public boolean InsertRecursoHumano(RecursoHumanoDTO p_Obj) throws Exception {
 
      String MPREFIX = " [ValidarUsuario(UsuarioDTO p_Obj)]";
        Integer salida=0;
        RecursoHumanoDTO objResult = null;
         final Connection  conn = ConexionPostgresql.IniciarConexion();
        try{
            logger.log(Level.INFO, CPREFIX + MPREFIX + "-> Inicio ejecución");
        
            // Obtenemos el objeto "Connection" e iniciamos la conexión
            Statement stmt = conn.createStatement();
            String sql="INSERT INTO public.recursohumano(" +
            "id, nombrepersona, fchnacimiento, direccion, comuna, region, " +
            "email, telefono, sexo, fchcontrato, departamento)" +
            "VALUES (nextval('rrhh'),'"+p_Obj.GetNombrePersona()+"','"+p_Obj.GetFchNacimiento()+"','"+p_Obj.GetDireccion()+"','"+p_Obj.GetComuna()+"','"+p_Obj.GetRegion()+"','" +
             ""+p_Obj.GetEmail()+"','"+p_Obj.GetTelefono()+"','"+p_Obj.GetSexo()+"','"+p_Obj.GetFchContrato()+"','"+p_Obj.GetDepartamento()+"');";
            
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
       else
       {return false;}    
    }

    @Override
    public boolean UpdateRecursoHumano(RecursoHumanoDTO p_Obj) throws Exception {
        
     Integer salida=0;
     final Connection  conn = ConexionPostgresql.IniciarConexion();        
     Statement stmt = conn.createStatement(); 
        
        String sql="UPDATE public.recursohumano " +
        " SET  nombrepersona='"+p_Obj.GetNombrePersona()+"', fchnacimiento='"+p_Obj.GetFchNacimiento()+"', direccion='"+p_Obj.GetDireccion()+"', comuna='"+p_Obj.GetComuna()+"'," +
        " region='"+p_Obj.GetRegion()+"', email='"+p_Obj.GetEmail()+"', telefono='"+p_Obj.GetTelefono()+"', sexo='"+p_Obj.GetSexo()+"', fchcontrato='"+p_Obj.GetFchContrato()+"',"
                + " departamento='"+p_Obj.GetDireccion()+"'"+
        " WHERE id="+p_Obj.GetId()+";";
        System.out.println("dataaccess.LoginDA.ValidarUsuario sql="+sql);

           int rs = stmt.executeUpdate(sql);
            salida=rs;
            
            stmt.close();
            if(salida>0)
            {return true;}
            else
            {return false;}    
    }

    @Override
    public boolean deleteRecursoHumano(RecursoHumanoDTO p_Obj) throws Exception {

             
     Integer salida=0;
     final Connection  conn = ConexionPostgresql.IniciarConexion();        
     Statement stmt = conn.createStatement(); 
        
        String sql="delete from recursohumano where  id="+p_Obj.GetId()+";";
        System.out.println("dataaccess.LoginDA.ValidarUsuario sql="+sql);

           int rs = stmt.executeUpdate(sql);
            salida=rs;
            
            stmt.close();
            if(salida>0)
            {return true;}
            else
            {return false;}    
    }
}
