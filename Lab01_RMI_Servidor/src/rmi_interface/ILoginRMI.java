/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_interface;

import dto.UsuarioDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author alejandro
 */
public interface ILoginRMI extends Remote{
    // metodo para validar un usuario actual del sistema desde base datos
    public UsuarioDTO ValidarUsuario(UsuarioDTO p_Usuario) throws RemoteException;
}
