/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import conf.Parameters;
import java.rmi.RemoteException;
import java.util.List;
import rmi.ConexionCliente;
import rmi_interface.*;
import dto.FinanzaDTO;
import java.util.ArrayList;

/**
 *
 * @author aialiagam
 */
public class FinanzaModel {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    private ConexionCliente conexionRMI;
    private static FinanzaModel instance = null;
    private IFinanzaRMI finanzaRMI;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="constructores">
    
    // constructor privado
    private FinanzaModel(){
        this.conexionRMI = new ConexionCliente();
    }
    
    // metodo para obtener la instancia del singleton
    public static FinanzaModel GetInstance(){
        if(FinanzaModel.instance == null){
            FinanzaModel.instance = new FinanzaModel();
        }
    
        return FinanzaModel.instance;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    public List<FinanzaDTO> buscarAll() throws Exception, RemoteException{
        FinanzaDTO objResult = null;
        List<FinanzaDTO> lista=new ArrayList<FinanzaDTO>();
        try{
            // Iniciamos el registro del RMI
            if(this.conexionRMI.iniciarRegistro(Parameters.PARAM_IP_SERVER_RMI, Parameters.PARAM_PORT_SERVER_RMI)){
                this.finanzaRMI = this.conexionRMI.getServidorFinanza();
            }    
            
            lista = this.finanzaRMI.GetListaFinanzasAll();
        }
        catch(RemoteException ex){
            throw ex;
        }
        catch(Exception ex){
            throw ex;
        }
        
        return lista;
    }
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    public FinanzaDTO buscarById( int id) throws Exception, RemoteException{
        FinanzaDTO objResult = null;
        try{
            // Iniciamos el registro del RMI
            if(this.conexionRMI.iniciarRegistro(Parameters.PARAM_IP_SERVER_RMI, Parameters.PARAM_PORT_SERVER_RMI)){
                this.finanzaRMI = this.conexionRMI.getServidorFinanza();
            }    
            
            objResult = this.finanzaRMI.GetFinanzaById(id);
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
    
    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    public boolean borrarFinanza( FinanzaDTO fin) throws Exception, RemoteException{
        boolean salida=false;
        try{
            // Iniciamos el registro del RMI
            if(this.conexionRMI.iniciarRegistro(Parameters.PARAM_IP_SERVER_RMI, Parameters.PARAM_PORT_SERVER_RMI)){
                this.finanzaRMI = this.conexionRMI.getServidorFinanza();
            }    
            
            salida = this.finanzaRMI.deleteFinanza(fin);
        }
        catch(RemoteException ex){
            throw ex;
        }
        catch(Exception ex){
            throw ex;
        }
        
        return salida;
    }
    // </editor-fold> 
    
     public boolean insertarFinanza( FinanzaDTO fin) throws Exception, RemoteException{
        boolean salida=false;
        try{
            // Iniciamos el registro del RMI
            if(this.conexionRMI.iniciarRegistro(Parameters.PARAM_IP_SERVER_RMI, Parameters.PARAM_PORT_SERVER_RMI)){
                this.finanzaRMI = this.conexionRMI.getServidorFinanza();
            }    
            
            salida = this.finanzaRMI.InsertarFinanza(fin);
        }
        catch(RemoteException ex){
            throw ex;
        }
        catch(Exception ex){
            throw ex;
        }
        
        return salida;
    }
    // </editor-fold> 
    
     public boolean update( FinanzaDTO fin) throws Exception, RemoteException{
        boolean salida=false;
        try{
            // Iniciamos el registro del RMI
            if(this.conexionRMI.iniciarRegistro(Parameters.PARAM_IP_SERVER_RMI, Parameters.PARAM_PORT_SERVER_RMI)){
                this.finanzaRMI = this.conexionRMI.getServidorFinanza();
            }    
            
            salida = this.finanzaRMI.UpdateFinanza(fin);
        }
        catch(RemoteException ex){
            throw ex;
        }
        catch(Exception ex){
            throw ex;
        }
        
        return salida;
    }
    // </editor-fold> 
}
