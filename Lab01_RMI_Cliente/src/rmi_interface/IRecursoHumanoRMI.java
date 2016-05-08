/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_interface;

import dto.RecursoHumanoDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author alejandro
 */
public interface IRecursoHumanoRMI extends Remote{
    // metodo para obtener un objeto RecursoHumanoDTO por un determinado id desde la base de datos
    public RecursoHumanoDTO GetRecursoHumanoById(int p_Id) throws Exception;
    // metodo para obtener una lista de RecursoHumanoDTO por un determinado filtro desde la base de datos
    public List<RecursoHumanoDTO> GetListRecursoHumanoAll() throws Exception;
    // metodo para insertar un nuevo registro de tipo "RecursoHumanoDTO" en la base de datos
    public boolean InsertRecursoHumano(RecursoHumanoDTO p_Obj) throws Exception;
    // metodo para actualizar un registro existente de tipo "RecursoHumanoDTO" en la base de datos
    public boolean UpdateRecursoHumano(RecursoHumanoDTO p_Obj) throws Exception;
    public boolean deleteRecursoHumano(RecursoHumanoDTO p_Obj) throws Exception;

}
