/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import dataaccess.QueryRRHHJdbc;
import dto.RecursoHumanoDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Logger;
import rmi_interface.IRecursoHumanoRMI;

/**
 *
 * @author alejandro
 */
public class ImplementacionRRHH extends UnicastRemoteObject implements IRecursoHumanoRMI {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    // ***
    // propiedades privadas
    private static String CPREFIX = "Lab01_RMI_Servidor.rmi.ImplementacionRRHH";
    private static Logger logger;
    private  QueryRRHHJdbc q=new QueryRRHHJdbc();
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="constructores">
    // ***
    // constructores
    public ImplementacionRRHH() throws RemoteException{
        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    // ***
    // metodos publicos
    @Override
    public RecursoHumanoDTO GetRecursoHumanoById(int p_Id) throws Exception {
        RecursoHumanoDTO resp = q.GetRecursoHumanoById(p_Id);
        return resp;
    }

    @Override
    public List<RecursoHumanoDTO> GetListRecursoHumanoAll() throws Exception {
        List<RecursoHumanoDTO> resp = q.GetListRecursoHumanoAll();
    
      return resp;
    }

    @Override
    public boolean InsertRecursoHumano(RecursoHumanoDTO p_Obj) throws Exception {
        boolean resp = q.InsertRecursoHumano(p_Obj);
      
        return resp;
      
    }
    @Override
    public boolean UpdateRecursoHumano(RecursoHumanoDTO p_Obj) throws Exception {
        boolean resp = q.UpdateRecursoHumano(p_Obj);
          return resp;

    }
    // </editor-fold>

    @Override
    public boolean deleteRecursoHumano(RecursoHumanoDTO p_Obj) throws Exception {
    boolean resp = q.deleteRecursoHumano(p_Obj);
  
        return resp;
    
    }
}
