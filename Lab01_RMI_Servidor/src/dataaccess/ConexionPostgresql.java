/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author alejandro
 */
public class ConexionPostgresql {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    // ***
    // propiedades privadas
    private static ConexionPostgresql conexionPostgresql=null;
    private static  Connection connection;
   
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="constructores">
    // ***
    // constructores
    public ConexionPostgresql(){
        // Dejamos estas variables por defecto en null

    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos privados">
    // ***
    // metodos privados

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    // ***
    // metodos publicos
    public  static Connection IniciarConexion() throws ClassNotFoundException, SQLException
    { 
      String connectString = "jdbc:postgresql://localhost:5432/sistdis_lab01";
      String user ="postgres";
      String password ="usach2016";
            // Iniciamos la conexión
            Class.forName("org.postgresql.Driver");            
           // connection = DriverManager.getConnection(connString);
            connection=DriverManager.getConnection(connectString, user, password);
            System.out.println("dataaccess.ConexionPostgresql.IniciarConexion() OK");
            return connection;
      
  
    }
    // </editor-fold>
    
    
    public static ConexionPostgresql getInstanceBD() throws Exception
{

	if(conexionPostgresql==null)
	{	
	 conexionPostgresql =new ConexionPostgresql();
	}
	return conexionPostgresql;
	
}

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
