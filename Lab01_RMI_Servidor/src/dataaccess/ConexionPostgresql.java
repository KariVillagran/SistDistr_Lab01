/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import conf.Parameters;
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
    
    // Instancia de la conexión actual
    private static ConexionPostgresql instance = null;
    // Conexión actual
    private Connection connection;   
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="constructores">
    // ***
    // constructores
    private ConexionPostgresql(){
        this.connection = null;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    // ***
    // metodos publicos
    public Connection IniciarConexion() throws ClassNotFoundException, SQLException{ 
        String connectString = Parameters.PARAM_DB_CONN_STRING; //"jdbc:postgresql://localhost:5432/sistdis_lab01";
        String user = Parameters.PARAM_DB_USER; //"postgres";
        String password = Parameters.PARAM_DB_PASS;  //"usach2016";
        
        // Iniciamos la conexión
        Class.forName("org.postgresql.Driver");            
        
        // connection = DriverManager.getConnection(connString);
        this.connection = DriverManager.getConnection(connectString, user, password);
        System.out.println("dataaccess.ConexionPostgresql.IniciarConexion() OK");
        
        return this.connection;
    }
  
    public static ConexionPostgresql getInstanceBD() throws Exception{
	if(ConexionPostgresql.instance == null){	
            ConexionPostgresql.instance = new ConexionPostgresql();
        }
        
	return ConexionPostgresql.instance;
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
    
    // </editor-fold>
}
