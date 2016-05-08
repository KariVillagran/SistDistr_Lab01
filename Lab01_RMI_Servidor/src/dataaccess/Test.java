/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import dto.FinanzaDTO;
import dto.RecursoHumanoDTO;
import dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import rmi.ImplementacionFinanzas;
import rmi.ImplementacionLogin;
import rmi.ImplementacionRRHH;

/**
 *
 * @author progre
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
           ImplementacionLogin implementacionLogin=new ImplementacionLogin();
         UsuarioDTO u=new UsuarioDTO();
         u.SetId(1);
        u.SetUserName("lguerra");
        u.SetPassword("123456");
        UsuarioDTO salida = implementacionLogin.ValidarUsuario(u);
        System.out.println("usuario nombre="+salida.GetNombreCompleto());
       
        RecursoHumanoDTO recursoHumanoDTO= new RecursoHumanoDTO();
        recursoHumanoDTO.SetId(6);
        recursoHumanoDTO.SetComuna("x");
        recursoHumanoDTO.SetDepartamento("Departamento");
        recursoHumanoDTO.SetDireccion("Direccion");
        recursoHumanoDTO.SetEmail("Email");
        recursoHumanoDTO.SetFchContrato(new Date());
        recursoHumanoDTO.SetFchNacimiento(new Date());
        recursoHumanoDTO.SetNombrePersona(" kuroro lucifer");
        recursoHumanoDTO.SetRegion("region");
        recursoHumanoDTO.SetSexo("sexo");
        recursoHumanoDTO.SetTelefono("telefono");
        ImplementacionRRHH qrrhh=new ImplementacionRRHH();

        boolean salidA = qrrhh.InsertRecursoHumano(recursoHumanoDTO);
        System.out.println("dataaccess.Test.main() lista ZZZZZZZZZZZZZZZ="+salidA);
        ImplementacionFinanzas implementacionFinanzas=new ImplementacionFinanzas();
        FinanzaDTO finanzaDTO=new FinanzaDTO();
        finanzaDTO.SetId(3);
        finanzaDTO.SetFchMovimiento(new Date());
        finanzaDTO.SetNombrePersonal("fdgdgdgf");
        finanzaDTO.SetSaldoMovimiento((double)300);
        boolean x = implementacionFinanzas.InsertarFinanza(finanzaDTO);
         System.out.println("dataaccess.Test.main() XXXXXXXXXXXXXXXXXXX"+x);
        //Integer salidaBorrar = qrrhh.borrar(recursoHumanoDTO);
        //System.out.println("dataaccess.Test.main() salida="+salidaBorrar); // por ok
        //ArrayList<RecursoHumanoDTO> lista = qrrhh.buscarUsuario(recursoHumanoDTO);
        //System.out.println("salida="+lista.get(0).GetNombrePersona());
        //System.out.println("salida="+lista.get(0).GetDireccion());
        
         
    }
    
}
