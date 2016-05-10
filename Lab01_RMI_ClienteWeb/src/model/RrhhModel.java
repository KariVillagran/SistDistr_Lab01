/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import conf.Parameters;
import dto.RecursoHumanoDTO;
import java.rmi.RemoteException;
import java.util.List;
import rmi.ConexionCliente;
import rmi_interface.IRecursoHumanoRMI;

/**
 *
 * @author alejandro
 */
public class RrhhModel {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    private ConexionCliente conexionRMI;
    private static RrhhModel instance = null;
    private IRecursoHumanoRMI rrhhRMI;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="constructores">
    
    // constructor privado
    private RrhhModel(){
        this.conexionRMI = new ConexionCliente();
    }
    
    // metodo para obtener la instancia del singleton
    public static RrhhModel GetInstance(){
        if(RrhhModel.instance == null){
            RrhhModel.instance = new RrhhModel();
        }
    
        return RrhhModel.instance;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    public List<RecursoHumanoDTO> GetListaRecursosHumanos() throws Exception, RemoteException{
        List<RecursoHumanoDTO> objResult = null;
        
        try{
            // Iniciamos el registro del RMI
            if(this.conexionRMI.iniciarRegistro(Parameters.PARAM_IP_SERVER_RMI, Parameters.PARAM_PORT_SERVER_RMI)){
                this.rrhhRMI = this.conexionRMI.getServidorRRHH();
            }
            
            objResult = this.rrhhRMI.GetListRecursoHumanoAll();
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
}
