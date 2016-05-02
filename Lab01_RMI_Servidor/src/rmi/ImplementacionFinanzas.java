/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FinanzaDTO> GetListaFinanzasByFiltro(FinanzaDTO p_Filtro) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean InsertarFinanza(FinanzaDTO p_Obj) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean UpdateFinanza(FinanzaDTO p_Obj) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // </editor-fold>
}
