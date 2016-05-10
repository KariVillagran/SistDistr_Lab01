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
    
    // <editor-fold defaultstate="collapsed" desc="metodos privados">
    
    private RecursoHumanoDTO failUser(){ 
        RecursoHumanoDTO salida=new RecursoHumanoDTO();
        salida.SetId(0);
        return salida;
    }  
  
    private String getFiltroDatos(RecursoHumanoDTO p_Obj){
       String _sqlQuery = "";
       
       if(p_Obj != null){
            List<String> whereClausesList = new ArrayList<String>();

            if(p_Obj.GetNombrePersona() != null){
                whereClausesList.add("nombrepersona like '%" + p_Obj.GetNombrePersona() + "%'");
            }
            if(p_Obj.GetDepartamento() != null){
                whereClausesList.add("departamento like '%" + p_Obj.GetDepartamento()+ "%'");
            }
            if(p_Obj.GetDireccion() != null){
                whereClausesList.add("direccion like '%" + p_Obj.GetDireccion()+ "%'");
            }
            if(p_Obj.GetRegion() != null){
                whereClausesList.add("region like '%" + p_Obj.GetRegion()+ "%'");
            }
            if(p_Obj.GetComuna() != null){
                whereClausesList.add("comuna like '%" + p_Obj.GetComuna() + "%'");
            }
            
            if(whereClausesList.size() > 0){
                _sqlQuery += "where ";
                
                for(int x = 0; x < whereClausesList.size(); x++){
                    _sqlQuery += whereClausesList.get(x);
                    
                    if((x + 1) < whereClausesList.size()){
                        _sqlQuery += " and ";
                    }
                }
            }
       }
       
       _sqlQuery += ";";
       
       return _sqlQuery;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    
    // <editor-fold defaultstate="collapsed" desc="GetRecursoHumanoById">
    @Override
    public RecursoHumanoDTO GetRecursoHumanoById(int p_Id) throws Exception {
        String MPREFIX = " [GetRecursoHumanoById(int p_Id)]";

        RecursoHumanoDTO objResult = null;
        final Connection  conn = ConexionPostgresql.getInstanceBD().IniciarConexion();
        ArrayList<RecursoHumanoDTO> lista=new ArrayList<>();
        try{
            logger.log(Level.INFO, String.format("%s %s %s", CPREFIX, MPREFIX, "-> Inicio ejecución"));
        
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
         
            logger.log(Level.INFO, String.format("%s %s %s", CPREFIX, MPREFIX, "-> Ejecución finalizada correctamente"));
        }
        catch(SQLException ex){
            logger.log(Level.SEVERE, String.format("%s %s %s",CPREFIX, MPREFIX, "-> Error al intentar Validar el Usuario. Detalle: " + ex.getMessage()));
        }
        finally{
            if(conn != null){
                conn.close();
            }
        }
        return objResult;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GetListRecursoHumanoAll">
    @Override
    public List<RecursoHumanoDTO> GetListRecursoHumanoAll() throws Exception {
        String MPREFIX = " [List<RecursoHumanoDTO> GetListRecursoHumanoAll()]";
        
        RecursoHumanoDTO objResult = null;
        final Connection  conn = ConexionPostgresql.getInstanceBD().IniciarConexion();
        ArrayList<RecursoHumanoDTO> lista=new ArrayList<>();
        
        try{
            logger.log(Level.INFO, String.format("%s %s %s", CPREFIX, MPREFIX, "-> Inicio ejecución"));
        
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
               objResult.SetDepartamento(rs.getString("departamento"));
               lista.add(objResult);
            }
            
            rs.close();
            stmt.close();
         
            logger.log(Level.INFO, String.format("%s %s %s", CPREFIX, MPREFIX, "-> Ejecución finalizada correctamente"));
        }
        catch(SQLException ex){
            logger.log(Level.SEVERE, String.format("%s %s %s", CPREFIX, MPREFIX, "-> Error al intentar Validar el Usuario. Detalle: " + ex.getMessage()));
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

    // <editor-fold defaultstate="collapsed" desc="GetListRecursoHumanoByFiltro">
    @Override
    public List<RecursoHumanoDTO> GetListRecursoHumanoByFiltro(RecursoHumanoDTO p_Obj) throws Exception{
        String MPREFIX = " [GetListRecursoHumanoByFiltro(RecursoHumanoDTO p_Obj)]";
        
        RecursoHumanoDTO objResult = null;
        final Connection  conn = ConexionPostgresql.getInstanceBD().IniciarConexion();
        ArrayList<RecursoHumanoDTO> lista=new ArrayList<>();
        try{
            logger.log(Level.INFO, String.format("%s %s %s", CPREFIX, MPREFIX, "-> Inicio ejecución"));
        
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
                    + "departamento FROM recursohumano " 
                    + getFiltroDatos(p_Obj);
            
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
               objResult.SetDepartamento(rs.getString("departamento"));
               lista.add(objResult);
            }
            
            rs.close();
            stmt.close();
         
            logger.log(Level.INFO, String.format("%s %s %s", CPREFIX, MPREFIX, "-> Ejecución finalizada correctamente"));
        }
        catch(SQLException ex){
            logger.log(Level.SEVERE, String.format("%s %s %s", CPREFIX, MPREFIX, "-> Error al intentar Validar el Usuario. Detalle: " + ex.getMessage()));
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
    
    // <editor-fold defaultstate="collapsed" desc="InsertRecursoHumano">
    @Override
    public boolean InsertRecursoHumano(RecursoHumanoDTO p_Obj) throws Exception {
 
      String MPREFIX = " [InsertRecursoHumano(RecursoHumanoDTO p_Obj)]";
        Integer salida=0;
        RecursoHumanoDTO objResult = null;
        final Connection  conn = ConexionPostgresql.getInstanceBD().IniciarConexion();
        try{
            logger.log(Level.INFO, String.format("%s %s %s", CPREFIX, MPREFIX, "-> Inicio ejecución"));
        
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
         
            logger.log(Level.INFO, String.format("%s %s %s", CPREFIX, MPREFIX, "-> Ejecución finalizada correctamente"));
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
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="UdpdateRecursoHumano">
    @Override
    public boolean UpdateRecursoHumano(RecursoHumanoDTO p_Obj) throws Exception {
        
     Integer salida=0;
     final Connection  conn = ConexionPostgresql.getInstanceBD().IniciarConexion();        
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="DeleteRecursoHumano">
    @Override
    public boolean deleteRecursoHumano(RecursoHumanoDTO p_Obj) throws Exception {

             
     Integer salida=0;
     final Connection  conn = ConexionPostgresql.getInstanceBD().IniciarConexion();        
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
    // </editor-fold>
    
    // </editor-fold>
}
