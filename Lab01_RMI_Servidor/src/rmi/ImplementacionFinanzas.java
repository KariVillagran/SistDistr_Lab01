/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import dataaccess.QueryFinanzaJdbc;
import dto.FinanzaDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Logger;
import rmi_interface.IFinanzaRMI;

/**
 *
 * @author alejandro
 */
public class ImplementacionFinanzas extends UnicastRemoteObject implements IFinanzaRMI {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    // ***
    // propiedades privadas
    private static String CPREFIX = "Lab01_RMI_Servidor.rmi.ImplementacionFinanzas";
    private static Logger logger;
    private QueryFinanzaJdbc qfj=new QueryFinanzaJdbc();
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="constructores">
    // ***
    // constructores
    public ImplementacionFinanzas() throws RemoteException{
        logger = Logger.getLogger(getClass().getName());
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    // ***
    // metodos publicos
    @Override
    public FinanzaDTO GetFinanzaById(int p_Id) throws RemoteException {
        FinanzaDTO resp = qfj.GetFinanzaById(p_Id);
        return resp;
    }

    @Override
    public List<FinanzaDTO> GetListaFinanzasAll() throws Exception {
        List<FinanzaDTO> resp = qfj.GetListaFinanzasAll();
        return resp;
    }

    @Override
    public boolean InsertarFinanza(FinanzaDTO p_Obj) throws Exception {
        boolean resp = qfj.InsertarFinanza(p_Obj);
       return resp;

    }

    @Override
    public boolean UpdateFinanza(FinanzaDTO p_Obj) throws Exception {
        boolean resp = qfj.UpdateFinanza(p_Obj);
     
       return resp;
    
    }
    // </editor-fold>

    @Override
    public boolean deleteFinanza(FinanzaDTO p_Obj) throws Exception {
 boolean resp = qfj.deleteFinanza(p_Obj);
        return resp;
 
    }
}
