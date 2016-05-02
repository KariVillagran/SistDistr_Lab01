/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_interface;

import dto.FinanzaDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author alejandro
 */
public interface IFinanzaRMI extends Remote{
    // Metodo para obtener una finanza de acuerdo a un determinado Id
    public FinanzaDTO GetFinanzaById(int p_Id) throws RemoteException;
    // Metodo para obtener una lista de finanzas de acuerdo a un determinado filtro
    public List<FinanzaDTO> GetListaFinanzasByFiltro(FinanzaDTO p_Filtro) throws RemoteException;
    // Metodo para insertar una finanza
    public boolean InsertarFinanza(FinanzaDTO p_Obj) throws RemoteException;
    // Metodo para actualziar una finanza
    public boolean UpdateFinanza(FinanzaDTO p_Obj) throws RemoteException;
}
