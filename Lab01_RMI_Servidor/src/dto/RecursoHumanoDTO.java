/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;

/**
 *
 * @author alejandro
 */
public class RecursoHumanoDTO {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    // ***
    // propiedades privadas
    private int id;
    private String nombrePersona;
    private Date fchNacimiento;
    private String direccion;
    private String comuna;
    private String region;
    private String email;
    private String telefono;
    private String sexo;
    private Date fchContrato;
    private String departamento;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="constructores">
    // ***
    // constructores
    public RecursoHumanoDTO(){
        this.id = 0;
        this.nombrePersona = null;
        this.fchNacimiento = null;
        this.direccion = null;
        this.comuna = null;
        this.region = null;
        this.email = null;
        this.telefono = null;
        this.sexo = null;
        this.fchContrato = null;
        this.departamento = null;
    }
    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc="metodos accesores">
    // ***
    // metodos accesores
    public int GetId(){
        return this.id;
    }
    
    public String GetNombrePersona(){
        return this.nombrePersona;
    }
    
    public Date GetFchNacimiento(){
        return this.fchNacimiento;
    }
    
    public String GetDireccion(){
        return this.direccion;
    }
    
    public String GetComuna(){
        return this.comuna;
    }
    
    public String GetRegion(){
        return this.region;
    }
    
    public String GetEmail(){
        return this.email;
    }
    
    public String GetTelefono(){
        return this.telefono;
    }
    
    public String GetSexo(){
        return this.sexo;
    }
    
    public Date GetFchContrato(){
        return this.fchContrato;
    }
    
    public String GetDepartamento(){
        return this.departamento;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos mutadores">
    // ***
    // metodos mutadores
    public void SetId(int id){
        this.id = id;
    }
    
    public void SetNombrePersona(String nombrePersona){
        this.nombrePersona = nombrePersona;
    }
    
    public void SetFchNacimiento(Date fchNacimiento){
        this.fchNacimiento = fchNacimiento;
    }
    
    public void SetDireccion(String direccion){
        this.direccion = direccion;
    }
    
    public void SetComuna(String comuna){
        this.comuna = comuna;
    }
    
    public void SetRegion(String region){
        this.region = region;
    }
    
    public void SetEmail(String email){
        this.email = email;
    }
    
    public void SetTelefono(String telefono){
        this.telefono = telefono;
    }
    
    public void SetSexo(String sexo){
        this.sexo = sexo;
    }
    
    public void SetFchContrato(Date fchContrato){
        this.fchContrato = fchContrato;
    }
    
    public void SetDepartamento(String departamento){
        this.departamento = departamento;
    }
    // </editor-fold>
}

