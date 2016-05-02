/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

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
    public RecursoHumanoDTO GetRecursoHumanoById(int p_Id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecursoHumanoDTO> GetListRecursoHumanoByFiltro(RecursoHumanoDTO p_Filtro) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean InsertRecursoHumano(RecursoHumanoDTO p_Obj) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean UpdateRecursoHumano(RecursoHumanoDTO p_Obj) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // </editor-fold>
}
