/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author alejandro
 */
public class ConexionPostgresql {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    // ***
    // propiedades privadas
    private Connection connection;
    private String connString;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="constructores">
    // ***
    // constructores
    public ConexionPostgresql(){
        // Dejamos estas variables por defecto en null
        this.connection = null;
        this.connString = null;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos privados">
    // ***
    // metodos privados
    private void GetConnectionString() throws Exception{
        try{
            // Obtenemos el connection string desde un archivo de configuración xml
            if(this.connString == null){
                Properties prop = new Properties();
                prop.loadFromXML(new File("ConfigurationParameters.xml").toURI().toURL().openStream());
                this.connString = prop.getProperty("connString");
            }
        }
        catch(Exception ex){
            throw ex;
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    // ***
    // metodos publicos
    public Connection IniciarConexion() throws Exception {
        try{
            this.GetConnectionString();
            
            // Iniciamos la conexión
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(connString);         
            
            return this.connection;
        }
        catch(Exception ex){
            throw ex;
        }
    }
    // </editor-fold>
}
